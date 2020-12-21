const express = require("express");
const { userByID } = require("../controllers/user.controller");
const { requireSignin } = require("../controllers/auth.controller");
const {
  create,
  photo,
  listByUser,
  listNewsFeed,
  like,
  unlike,
  comment,
  uncomment,
  remove,
  postByID,
  isPoster,
} = require("../controllers/post.controller");

const router = express.Router();

router.route("/posts/new/:userId").post(requireSignin, create);

router.route("/posts/photo/:postId").get(photo);

router.route("/posts/by/:userId").get(requireSignin, listByUser);

router.route("/posts/feed/:userId").get(requireSignin, listNewsFeed);

router.route("/posts/like").put(requireSignin, like);
router.route("/posts/unlike").put(requireSignin, unlike);

router.route("/posts/comment").put(requireSignin, comment);
router.route("/posts/uncomment").put(requireSignin, uncomment);

router.route("/posts/:postId").delete(requireSignin, isPoster, remove);

router.param("userId", userByID);
router.param("postId", postByID);

moudle.exports = router;
