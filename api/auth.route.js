const express = require("express");
const router = express.Router();

// Load Controllers
const {
  registerController,
  activationController,
} = require("../controllers/auth.controller");

const { validSign } = require("../helpers/valid");

router.post("/register", validSign, registerController);
router.post("/activation", activationController);

module.exports = router;
