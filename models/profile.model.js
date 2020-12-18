const mongoose = require("mongoose");
const Schema = mongoose.Schema;
const { ObjectId } = mongoose.Schema.Types;

const ProfileSchema = new Schema(
  {
    user: {
      type: ObjectId,
      ref: "User",
    },
    name: {
      type: String,
      required: true,
      max: 50,
    },
    gender: {
      type: String,
      required: true,
    },
    bio: {
      type: String,
    },
    social: {
      youtube: {
        type: String,
      },
      facebook: {
        type: String,
      },
      instagram: {
        type: String,
      },
    },
    Photo: {
      type: Buffer,
    },
    PhotoType: {
      type: String,
    },
    Followers: [
      {
        type: ObjectId,
        ref: "User",
      },
    ],
    Following: [
      {
        type: ObjectId,
        ref: "User",
      },
    ],
    Bookmarks: [
      {
        type: ObjectId,
        ref: "Post",
      },
    ],
  },
  {
    timestamps: true,
  }
);

module.exports = Profile = mongoose.model("myProfile", ProfileSchema);
