const Tag = require("../models/tags");
const Question = require("../models/questions");
const Answer = require("../models/answers");

const addTag = async (tname) => {
    try{
    const existingTag = await Tag.findOne({ name: tname });
    if (existingTag) {
        return existingTag._id;
    }
    
    const newTag = new Tag({
        name: tname
    });

    const savedTag = await newTag.save();
    return savedTag._id;
} catch (error) {
    console.error("Failed to add tag:", error);}
};

const getQuestionsByOrder = async (order) => {
    try{
    let questions;
    switch (order) {
        case 'active':
            questions = await Question.find({ status: { $ne: 'deleted' } }).populate('answers').populate('tags');
            questions.sort((a, b) => {
                if (a.answers.length === 0 && b.answers.length === 0) {
                    return b.ask_date_time - a.ask_date_time;
                }

                if (a.answers.length === 0) {
                    return 1;
                }

                if (b.answers.length === 0) {
                    return -1;
                }

                aMostRecentAnswer = a.answers.sort((a, b) => b.ans_date_time - a.ans_date_time)[0];
                bMostRecentAnswer = b.answers.sort((a, b) => b.ans_date_time - a.ans_date_time)[0];

                aAnsDateTime = aMostRecentAnswer.ans_date_time;
                bAnsDateTime = bMostRecentAnswer.ans_date_time;

                if (aAnsDateTime.getTime() === bAnsDateTime.getTime()) {
                    return b.ask_date_time - a.ask_date_time;
                }

                return bAnsDateTime - aAnsDateTime;
                
            });
            break;
        case 'unanswered':
            questions = await Question.find({ status: { $ne: 'deleted' } }).populate('answers').populate('tags');

            questions.sort((a, b) => b.ask_date_time - a.ask_date_time);
            questions = questions.filter(question => question.answers.length === 0);
            break;
        case 'newest':
            questions = await Question.find({ status: { $ne: 'deleted' } }).populate('answers').populate('tags');

            questions.sort((a, b) => b.ask_date_time - a.ask_date_time);
            break;
        default:
            throw new Error(`Unknown order: ${order}`);
    }
    return questions;
} catch (error) {
    console.error("Failed to get question by order:", error);}
}

const filterQuestionsBySearch = (qlist, search) => {
    try{
    if (!search.trim()) {
        return qlist; 
    }

    const keywords = [];
    const tagNames = [];
    const searchParts = search.split(' ')
    
    searchParts.forEach(part => {
        if (part.startsWith('[') && part.endsWith(']')) {
            tagNames.push(part.slice(1, -1).toLowerCase());
        } else {
            keywords.push(part.toLowerCase());
        }
    });

    return qlist.filter(question => {
        const keywordMatch = keywords.some(keyword => {
            return question.title.toLowerCase().includes(keyword) || question.text.toLowerCase().includes(keyword);
        });

        const tagMatch = tagNames.some(tagName => {
            return question.tags.some(tag => tag.name.toLowerCase() === tagName);
        });

        return keywordMatch || tagMatch;
    });
} catch (error) {
    console.error("Failed to filter question by search:", error);}
};

const filterQuestionsByUser = (qlist, user) => {
    try{
	console.log(`qlist in filterQuestionsByUser: ${JSON.stringify(qlist)}`)
	console.log(`user in filterQuestionsByUser: ${user}`)
    return qlist.filter(question => {
		console.log(`checking question ${question} for user ${user}`)
        const userMatch = (question.asked_by_id.toString() === user);
        return userMatch;
    });
} catch (error) {
    console.error("Failed to filter question by user:", error);}
};


module.exports = { addTag, getQuestionsByOrder, filterQuestionsBySearch,filterQuestionsByUser};
