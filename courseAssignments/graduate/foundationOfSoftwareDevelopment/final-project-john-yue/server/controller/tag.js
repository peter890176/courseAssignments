const express = require("express");
const Tag = require("../models/tags");
const Question = require("../models/questions");

const router = express.Router();

const getTagsWithQuestionNumber = async (req, res) => {
    try{
    let questions = await Question.find({ status: { $ne: 'deleted' } }).populate('tags');
    let tags = await Tag.find();

    let tagQuestionCount = [];
    tags.forEach(tag => {
        let count = 0;
        questions.forEach(question => {
            if (question.tags.map(t => t.name).includes(tag.name)) {
                count++;
            }
        });
        if (count>0){
        tagQuestionCount.push({ name: tag.name, qcnt: count })}
    });

    res.status(200).json(tagQuestionCount);
} catch (error) {
    console.error("Failed to get tag with question number:", error);}
};

router.get("/getTagsWithQuestionNumber", getTagsWithQuestionNumber);

module.exports = router;
