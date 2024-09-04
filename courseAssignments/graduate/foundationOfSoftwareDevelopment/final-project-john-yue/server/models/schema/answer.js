const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const answerSchema = new mongoose.Schema({
    text: {
        type: String,
        required: true, 
    },
    ans_by: {
        type: String,
        required: true, 
    },
    ans_date_time: {
        type: Date,
        required: true, 
    },
    ans_by_id: {
		type: Schema.Types.ObjectId,
		ref: "User",
		required: true
	},
}, { collection: "Answer" });

module.exports = answerSchema;

