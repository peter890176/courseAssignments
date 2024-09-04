const express = require("express");
const Question = require("../models/questions");
const User = require("../models/users");
const { addTag, getQuestionsByOrder, filterQuestionsBySearch,filterQuestionsByUser } = require('../utils/question');

const router = express.Router();

// To get Questions by Filter
const getQuestionsByFilter = async (req, res) => {
    try{
    let questions = await getQuestionsByOrder(req.query.order);
    questions = filterQuestionsBySearch(questions, req.query.search);

    res.status(200).json(questions);
} catch (error) {
    console.error("Failed to get question by filter:", error);}
};

// To get Questions by Id
const getQuestionById = async (req, res) => {
    try{
    let question = await Question.findOneAndUpdate({ _id: req.params.qid }, { $inc: { views: 1 } }, { new: true }).populate('answers');
    res.status(200).json(question);
} catch (error) {
    console.error("Failed to get question by id:", error);}
};

// To add Question
const addQuestion = async (req, res) => {
    try{
    let questionData = req.body;
    let tagIds = [];

    for (const tagName of questionData.tags) {
        tagIds.push(await addTag(tagName));
    }

    questionData.tags = tagIds;

	const u = await User.findOne({ name: questionData.asked_by });
	questionData.asked_by_id = u._id;

    const createdQuestion = await Question.create(questionData);
    res.status(200).json(createdQuestion);
} catch (error) {
    console.error("Failed to add question:", error);}
};

// To get Questions by Filter
const getQuestionsByUserFilter = async (req, res) => {
    try{
    const order = req.query.order || 'newest';
    const user = req.params.user;
    let userQuestions = await getQuestionsByOrder(order);
    userQuestions = filterQuestionsByUser(userQuestions, user);
    res.status(200).json(userQuestions);
} catch (error) {
    console.error("Failed to get question by filter:", error);}
};

const toggleQuestionById = async (req, res) => {
    try{
    let question = await Question.findById(req.params.qid);

    if(question.lock=='locked'){
        question.lock='unlocked';
    }
    else{question.lock='locked'}

    let lockReason = "";
    let lockByWhichAdminId = "";
    
    if (question.lock === 'locked') {
        lockReason= req.body.lockReason;
        
        const u = await User.findOne({ name: req.body.username });
        lockByWhichAdminId = u._id;
    }
    else {
        lockReason= "";
        lockByWhichAdminId = "";}

    let updatedQuestion;
    if (lockByWhichAdminId===""){
        updatedQuestion=await Question.findOneAndUpdate({ _id: req.params.qid }, { lock:question.lock, lockReason: lockReason},{ new: true }).populate('answers');
    }else{
        updatedQuestion= await Question.findOneAndUpdate({ _id: req.params.qid }, { lock:question.lock, lockReason: lockReason, lockByWhichAdminId:lockByWhichAdminId},{ new: true }).populate('answers');
}
    console.log("toggleLocked Question:")
    console.log(updatedQuestion);
    res.status(200).json(updatedQuestion);
} catch (error) {
    console.error("Failed to toggle question lock:", error);}
};

const deleteQuestionById = async (req, res) => {
    try{
    let question = await Question.findOneAndUpdate({ _id: req.params.qid }, { status:"deleted" },{ new: true }).populate('answers');
    res.status(200).json(question);
} catch (error) {
        console.error("Failed to delete question by id:", error);}
};


router.get("/getQuestion", getQuestionsByFilter);
router.get("/getQuestionById/:qid", getQuestionById);
router.post("/addQuestion", addQuestion);
router.get("/getQuestionByUserFilter/:user", getQuestionsByUserFilter);
router.post("/toggleQuestionById/:qid", toggleQuestionById);
router.post("/deleteQuestionById/:qid", deleteQuestionById);

module.exports = router;
