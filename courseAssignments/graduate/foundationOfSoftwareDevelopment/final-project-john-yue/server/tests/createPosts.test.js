const mongoose = require("mongoose")
const supertest = require("supertest")
const Question = require('../models/questions');
const User = require('../models/users');
const Tag = require('../models/tags');
const { resetDB, getMockQuestionsWithPopulatedAnswersAndTags, toObjectAndTransform } = require('./utils')

let server;
let mockQuestionsWithPopulatedAnswersAndTags

beforeAll(() => {
	resetDB();
	server = require("../server");
})

afterAll(async() => {
	server.close();
	await mongoose.disconnect()
});

describe("POST /addQuestion", () => {
	it('can add a question', async () => {
		mockQuestionsWithPopulatedAnswersAndTags = await getMockQuestionsWithPopulatedAnswersAndTags()

		const mockQuestion = toObjectAndTransform(mockQuestionsWithPopulatedAnswersAndTags)[0]
		const askedById = mockQuestion.asked_by_id
		delete mockQuestion.asked_by_id

		const userFindOneMock = jest.fn().mockResolvedValueOnce({_id: askedById})
		const questionCreateMock = jest.fn().mockResolvedValueOnce({...mockQuestion, asked_by_id: askedById})

		User.findOne = userFindOneMock
		Question.create = questionCreateMock

		const response = await supertest(server).post('/question/addQuestion').send(mockQuestion);

		expect(response.status).toBe(200);
		expect(response.body).toEqual({...mockQuestion, asked_by_id: askedById});
		expect(userFindOneMock).toHaveBeenCalled();
		expect(questionCreateMock).toHaveBeenCalled();
	})
})
