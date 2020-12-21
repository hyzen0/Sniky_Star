const express = require("express");
const { userCtrl } = require("../controllers/user.controller");
const { requireSignin } = require("../controllers/auth.controller");
const {
  create,
  video,
  listPopular,
  listRelated,
  listByUser,
  read,
} = require("../controllers/media.controller");

const router = express.Router();

router.route("/api/media/new/:userId").post(requireSignin, create);

router.route("/api/media/video/:mediaId").get(mediaCtrl.video);

router.route("/api/media/popular").get(mediaCtrl.listPopular);

router.route("/api/media/related/:mediaId").get(mediaCtrl.listRelated);

router.route("/api/media/by/:userId").get(mediaCtrl.listByUser);

router
  .route("/api/media/:mediaId")
  .get(mediaCtrl.incrementViews, mediaCtrl.read)
  .put(authCtrl.requireSignin, mediaCtrl.isPoster, mediaCtrl.update)
  .delete(authCtrl.requireSignin, mediaCtrl.isPoster, mediaCtrl.remove);

router.param("userId", userCtrl.userByID);
router.param("mediaId", mediaCtrl.mediaByID);

module.exports = router;
