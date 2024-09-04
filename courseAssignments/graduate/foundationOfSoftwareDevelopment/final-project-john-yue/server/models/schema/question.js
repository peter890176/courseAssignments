const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const questionSchema = new Schema({
    title: {
        type: String,
        required: true, 
    },
    text: {
        type: String,
        required: true, 
    },
    asked_by: {
        type: String,
        required: true, 
    },
	asked_by_id: {
		type: Schema.Types.ObjectId,
		ref: "User",
		required: true
	},
    ask_date_time: {
        type: Date,
        required: true, 
    },
    views: {
        type: Number,
        default: 0, 
    },
    tags: [{
        type: Schema.Types.ObjectId,
        ref: "Tag"
    }],
    answers: [{
        type: Schema.Types.ObjectId,
        ref: "Answer"
    }],
    votes: {
        type: Number,
        default: 0, 
    },
    status: {
        type: String,
        required: true,
        default: "normal" 
    },
    lock: {
        type: String,
        required: true,
        default: "unlocked" 
    },
    votedUsers: [{
		id: {
			type: mongoose.Schema.Types.ObjectId,
			required: true,
			ref: "User"
		},
        voteType: {
            type: String,
            required: true,
            enum: ["up", "down"]
        }
    }],
    lockReason:{
    type: String,
    default: ""},
    lockByWhichAdminId:{
        type: mongoose.Schema.Types.ObjectId,
			ref: "User"}

}, { collection: "Question" });

module.exports = questionSchema;
