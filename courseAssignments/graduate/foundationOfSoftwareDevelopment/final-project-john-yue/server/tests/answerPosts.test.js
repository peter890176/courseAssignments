const mongoose = require("mongoose");
const supertest = require("supertest");
const { resetDB, getMockAnswers, toObjectAndTransform } = require("./utils");
const Question = require("../models/questions");
const Answer = require("../models/answers");

let server

beforeAll(() => {
	resetDB();
	server = require("../server");
})

afterAll(async() => {
	server.close();
	await mongoose.disconnect()
});

afterEach(() => {
	resetDB();
	jest.restoreAllMocks();
})

describe('POST /addAnswer', () => {
	it('can add an answer', async () => {
		let mockAnswers = await getMockAnswers()
		mockQID = 1

		const answerCreateMock = jest.fn().mockResolvedValueOnce(mockAnswers[0]);
		const questionFindOneAndUpdateMock = jest.fn()
		
		jest.spyOn(Answer, 'create').mockImplementation(answerCreateMock);
		jest.spyOn(Question, 'findOneAndUpdate').mockImplementation(questionFindOneAndUpdateMock);

		console.log(`mockAnswers[0].toObject(): ${JSON.stringify(mockAnswers[0].toObject())}`)
		const data = { qid: mockQID, ans: mockAnswers[0].toObject()}
		const response = await supertest(server).post('/answer/addAnswer').send(data);

		expect(response.status).toBe(200);
		expect(response.body).toEqual(toObjectAndTransform(mockAnswers)[0]);

		expect(answerCreateMock).toHaveBeenCalled();
		expect(questionFindOneAndUpdateMock).toHaveBeenCalledWith(
			{ _id: mockQID},
			{ $push: { answers: { $each: [mockAnswers[0]._id], $position: 0 } } },
			{ new: true }
		)
	})
})
