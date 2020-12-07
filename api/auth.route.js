const express = require("express");
const router = express.Router();

// Load Controllers
const { registerController } = require("../controllers/auth.controller");

const { validSign } = require("../helpers/valid");

router.post("/register", validSign, registerController);

module.exports = router;
