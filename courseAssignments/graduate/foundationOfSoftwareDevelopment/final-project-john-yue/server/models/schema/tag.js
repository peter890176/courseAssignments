const mongoose = require("mongoose");

const tagSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true, 
        unique: true, 
    }
}, { collection: "Tag" });

module.exports = tagSchema;
