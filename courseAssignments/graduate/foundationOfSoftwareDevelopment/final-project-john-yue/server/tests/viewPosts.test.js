const mongoose = require("mongoose");
const supertest = require("supertest")
const Question = require('../models/questions');
const Tag = require('../models/tags');
const { getMockQuestionsWithPopulatedAnswers, getMockQuestionsWithPopulatedAnswersAndTags, resetDB, toObjectAndTransform } = require('./utils')

let server;
let mockQuestionsWithPopulatedAnswers
let mockQuestionsWithPopulatedAnswersAndTags

beforeAll(() => {
	resetDB();
	server = require("../server");
})

afterAll(async() => {
	server.close();
	await mongoose.disconnect()
});

describe('GET /getQuestionById/:qid', () => {
	it('can get question by Id', async () => {
		mockQuestionsWithPopulatedAnswers = await getMockQuestionsWithPopulatedAnswers()
		mockQuestionsWithPopulatedAnswers = toObjectAndTransform(mockQuestionsWithPopulatedAnswers)
		updatedMockQuestion = {...mockQuestionsWithPopulatedAnswers[0], views: mockQuestionsWithPopulatedAnswers[0].views + 1}

		const findOneAndUpdateMock = jest.fn().mockImplementation(() => (
			{populate: jest.fn().mockResolvedValueOnce(updatedMockQuestion)}
		)); 
	
		Question.findOneAndUpdate = findOneAndUpdateMock;

		const response = await supertest(server).get('/question/getQuestionById/1');

		expect(response.status).toBe(200);
		expect(response.body).toEqual(updatedMockQuestion);
		expect(findOneAndUpdateMock).toHaveBeenCalled();
	})

})

describe('GET /getQuestion', () => {
	it('can get all questions', async () => {
		mockQuestionsWithPopulatedAnswersAndTags = await getMockQuestionsWithPopulatedAnswersAndTags()
		const noDeletedQuestions = mockQuestionsWithPopulatedAnswersAndTags.slice(0, -1)
		const findMock = jest.fn().mockImplementation(() => (
			{populate: jest.fn().mockImplementation(() => (
				{populate: jest.fn().mockResolvedValueOnce(
					noDeletedQuestions
				)})
			)}
		)); 

		const orderedResult = [
			mockQuestionsWithPopulatedAnswersAndTags[1].title,
			mockQuestionsWithPopulatedAnswersAndTags[0].title,
			mockQuestionsWithPopulatedAnswersAndTags[2].title
		]
		
		Question.find = findMock;
		const response = await supertest(server).get('/question/getQuestion?order=newest&search=');
		expect(response.status).toBe(200);
		expect(response.body.map(q => q.title)).toEqual(orderedResult);
		expect(findMock).toHaveBeenCalled();
	})
})
