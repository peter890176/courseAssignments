const express = require("express");
const Answer = require("../models/answers");
const Question = require("../models/questions");
const User = require("../models/users");
const router = express.Router();

// Adding answer
const addAnswer = async (req, res) => {
    try{
    console.log(`addAnswer in controller received: ${JSON.stringify(req.body)}`)
    let answerData = req.body.ans;
    const u = await User.findOne({ name: answerData.ans_by});
	answerData.ans_by_id = u._id;
    let newAnswer = await Answer.create(answerData);
    await Question.findOneAndUpdate({ _id: req.body.qid }, { $push: { answers: { $each: [newAnswer._id], $position: 0 } } }, { new: true });

    res.status(200).json(newAnswer);
} catch (error) {
    console.error("Failed to add answer:", error);}
};

router.post("/addAnswer", addAnswer);

module.exports = router;
