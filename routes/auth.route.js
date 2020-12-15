const User = require("../models/auth.model");
const express = require("express");
const router = express.Router();
const { errorHandler } = require("../helpers/dbErrorHandling");
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

router.post("/login", signinController);

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
  const { number } = req.body;
  nexmo.verify.request(
    {
      number: "91" + number,
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
          data: [{ number }],
        });
      }
    }
  );
});

router.post("/verifyOtp", (req, res) => {
  const { username, number, password, code } = req.body;
  nexmo.verify.check(
    {
      request_id: reqId,
      code: code,
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
          User.findOne({
            number,
          }).exec((err, user) => {
            if (user) {
              return res.json({
                code: 400,
                msg: "Number is already Registerd",
              });
            } else {
              const user = new User({
                username,
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
                    msg: "success",
                    data: [{ user }],
                  });
                }
              });
            }
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

module.exports = router;
