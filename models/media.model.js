const mongoose = require("mongoose");
const { ObjectId } = mongoose.Schema.Types;

const mediaSchema = new mongoose.Schema({
  title: {
    type: String,
    required: "title is required",
  },
  description: String,
  genre: String,
  views: {
    type: Number,
    default: 0,
  },
  postedBy: {
    type: ObjectId,
    ref: "User",
  },
  created: {
    type: Date,
    default: Date.now,
  },
  updated: {
    type: Date,
  },
});

module.exports = mongoose.model("Media", mediaSchema);
