const express = require("express");
const { userByID } = require("../controllers/user.controller");
const { requireSignin } = require("../controllers/auth.controller");
const {
  create,
  video,
  listPopular,
  listRelated,
  listByUser,
  read,
  mediaByID,
  remove,
  isPoster,
  update,
  incrementViews,
} = require("../controllers/media.controller");

const router = express.Router();

router.post("/media/new/:userId", requireSignin, create);

router.get("/media/video/:mediaId", video);

router.get("/media/popular", listPopular);

router.get("/media/related/:mediaId", listRelated);

router.get("/media/by/:userId", listByUser);

router
  .route("/media/:mediaId")
  .get(incrementViews, read)
  .put(requireSignin, isPoster, update)
  .delete(requireSignin, isPoster, remove);

router.param("userId", userByID);
router.param("mediaId", mediaByID);

module.exports = router;
