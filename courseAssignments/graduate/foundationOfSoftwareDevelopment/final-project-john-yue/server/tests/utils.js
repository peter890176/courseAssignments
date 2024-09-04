const mongoose = require("mongoose");
const Tag = require("../models/tags");
const Question = require("../models/questions");
const Answer = require("../models/answers");
const User = require("../models/users");
const { exec } = require("child_process");

const resetDB = () => {
	exec('node ../controller/destroy.js')
	exec('node ../controller/init.js')
}

const toObjectAndTransform = (docs) => {
	return docs.map(doc => doc.toObject({
		transform: (doc, ret) => {
			Object.keys(ret).forEach(key => {
				if (ret[key] instanceof Date) {
					ret[key] = ret[key].toISOString();
				} else if (ret[key] instanceof mongoose.Types.ObjectId) {
					ret[key] = ret[key].toString();
				} else if (Array.isArray(ret[key])) {
					ret[key] = ret[key].map((element) => {
						if (element instanceof mongoose.Types.ObjectId) {
							return element.toString();
						} else {
						return element;
						}
					})
				}
			})
			return ret;
		}
	}));
}

const getMockQuestionsWithPopulatedAnswers = async () => {
	const results = await Question.find().collation({ locale: 'en', strength: 2 }).sort({ title: 1 }).populate('answers')

	return results
}

const getMockQuestionsWithPopulatedAnswersAndTags = async () => {
	const results = await Question.find().collation({ locale: 'en', strength: 2 }).sort({ title: 1 }).populate('tags').populate('answers')

	return results
}

const getMockAnswers = async () => {
	const results = await Answer.find().collation({ locale: 'en', strength: 2 }).sort({ text: 1 })
	return results
}

module.exports = { getMockQuestionsWithPopulatedAnswers, getMockQuestionsWithPopulatedAnswersAndTags, resetDB, toObjectAndTransform, getMockAnswers } 
