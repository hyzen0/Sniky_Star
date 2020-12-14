const User = require("../models/auth.model");
const express = require("express");
const router = express.Router();
const Nexmo = require("nexmo");
const nexmo = new Nexmo({
  apiKey: "a54a75f4",
  apiSecret: "KqTuwzPkj9WmM9Ct",
});
let reqId;
let Verified = false;

// Load Controllers
const {
  registerController,
  activationController,
  signinController,
  forgotPasswordController,
  resetPasswordController,
  googleController,
  facebookController,
} = require("../controllers/auth.controller");

const {
  validSign,
  validLogin,
  forgotPasswordValidator,
  resetPasswordValidator,
} = require("../helpers/valid");

router.post("/register", validSign, registerController);

router.post("/login", validLogin, signinController);

router.post("/activation", activationController);

// forgot reset password
router.put(
  "/forgotpassword",
  forgotPasswordValidator,
  forgotPasswordController
);

router.put("/resetpassword", resetPasswordValidator, resetPasswordController);

// Google and Facebook Login
router.post("/googlelogin", googleController);
router.post("/facebooklogin", facebookController);

router.post("/sendotp", (req, res) => {
  nexmo.verify.request(
    {
      number: "91" + req.body.number,
      brand: "Sniky Star",
      code_length: "4",
    },
    (err, result) => {
      if (err) {
        console.log(err);
        return res.json({
          code: "400",
          msg: err,
        });
      } else {
        reqId = result.request_id;

        return res.json({
          code: 200,
          msg: "Sent",
        });
      }
    }
  );
});

router.post("/verifyOtp", (req, res) => {
  nexmo.verify.check(
    {
      request_id: reqId,
      code: req.body.code,
    },
    (err, result) => {
      if (err) {
        console.log(err);
        return res.json({
          code: 400,
          msg: "failed",
        });
      } else {
        if (result.status === "0") {
          Verified = true;
          return res.json({
            code: 200,
            msg: "Verified",
          });
        } else {
          res.json({
            code: 400,
            msg: "Failed",
          });
        }
      }
    }
  );
});

router.post("/getinfo", (req, res) => {
  const { name, number, password } = req.body;
  if (Verified) {
    User.findOne({
      number,
    }).exec((err, user) => {
      if (user) {
        return res.json({ code: 400, msg: "Number is already Registerd" });
      } else {
        const user = new User({
          name,
          number,
          password,
        });
        user.save((err, user) => {
          if (err) {
            console.log("Save error", errorHandler(err));
            return res.json({
              code: 401,
              msg: errorHandler(err),
            });
          } else {
            return res.json({
              code: 200,
              data: user,
            });
          }
        });
      }
    });
  } else {
    return res.json(Verified);
  }
});

module.exports = router;
