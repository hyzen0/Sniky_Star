const User = require("../models/auth.model");
const extend = require("lodash/extend");
const { errorHandler } = require("../helpers/dbErrorHandling");
const formidable = require("formidable");
const fs = require("fs");

//Load user and append to req.

exports.userByID = async (req, res, next, id) => {
  try {
    let user = await User.findById(id);
    if (!user)
      return res.status("400").json({
        error: "User not found",
      });
    req.profile = user;
    next();
  } catch (err) {
    return res.status("400").json({
      error: "Could not retrieve user",
    });
  }
};

exports.read = (req, res) => {
  req.profile.hashed_password = undefined;
  req.profile.salt = undefined;
  return res.json(req.profile);
};

exports.list = async (req, res) => {
  try {
    let users = await User.find().select("name email updated created");
    res.json(users);
  } catch (err) {
    return res.status(400).json({
      error: errorHandler(err),
    });
  }
};

exports.update = (req, res) => {
  let form = new formidable.IncomingForm();
  form.keepExtensions = true;
  form.parse(req, async (err, fields, files) => {
    if (err) {
      return res.status(400).json({
        error: "Photo could not be uploaded",
      });
    }
    let user = req.profile;
    user = extend(user, fields);
    user.updated = Date.now();
    if (files.photo) {
      user.photo.data = fs.readFileSync(files.photo.path);
      user.photo.contentType = files.photo.type;
    }
    try {
      await user.save();
      user.hashed_password = undefined;
      user.salt = undefined;
      res.json(user);
    } catch (err) {
      return res.status(400).json({
        error: errorHandler(err),
      });
    }
  });
};

exports.remove = async (req, res) => {
  try {
    let user = req.profile;
    let deletedUser = await user.remove();
    deletedUser.hashed_password = undefined;
    deletedUser.salt = undefined;
    res.json(deletedUser);
  } catch (err) {
    return res.status(400).json({
      error: errorHandler(err),
    });
  }
};

exports.photo = (req, res, next) => {
  if (req.profile.photo.data) {
    res.set("Content-Type", req.profile.photo.contentType);
    return res.send(req.profile.photo.data);
  }
  next();
};

exports.defaultPhoto = (req, res) => {
  return res.sendFile(process.cwd());
};

exports.addFollowing = async (req, res, next) => {
  const { userId, followId } = req.body;
  try {
    await User.findByIdAndUpdate(userId, {
      $push: { following: followId },
    });
    next();
  } catch (err) {
    return res.status(400).json({
      error: errorHandler(err),
    });
  }
};

exports.addFollower = async (req, res) => {
  const { userId, followId } = req.body;
  try {
    let result = await User.findByIdAndUpdate(
      followId,
      { $push: { followers: userId } },
      { new: true }
    )
      .populate("following", "_id name")
      .populate("followers", "_id name")
      .exec();
    result.hashed_password = undefined;
    result.salt = undefined;
    res.json(result);
  } catch (err) {
    return res.status(400).json({
      error: errorHandler(err),
    });
  }
};

exports.removeFollowing = async (req, res, next) => {
  const { userId, unfollowId } = req.body;
  try {
    await User.findByIdAndUpdate(userId, {
      $pull: { following: unfollowId },
    });
    next();
  } catch (err) {
    return res.status(400).json({
      error: errorHandler(err),
    });
  }
};
exports.removeFollower = async (req, res) => {
  const { userId, unfollowId } = req.body;
  try {
    let result = await User.findByIdAndUpdate(
      unfollowId,
      { $pull: { followers: userId } },
      { new: true }
    )
      .populate("following", "_id name")
      .populate("followers", "_id name")
      .exec();
    result.hashed_password = undefined;
    result.salt = undefined;
    res.json(result);
  } catch (err) {
    return res.status(400).json({
      error: errorHandler(err),
    });
  }
};

exports.findPeople = async (req, res) => {
  const { following, _id } = req.profile;
  following.push(_id);
  try {
    let users = await User.find({ _id: { $nin: following } }).select("name");
    res.json(users);
  } catch (err) {
    return res.status(400).json({
      error: errorHandler(err),
    });
  }
};
