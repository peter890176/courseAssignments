const mongoose = require("mongoose");
const supertest = require("supertest");
const { resetDB } = require("./utils");
const Question = require('../models/questions');
const User = require('../models/users');

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

describe('POST question/toggleQuestionById/:qid', () => {
	it('can lock a question', async () => {
		const mockQID = 1
		const mockQuestion = { _id: mockQID, locked: false }
		const mockLockReason = 'This question is locked'
		const mockLockByWhichAdmin = 'saltyPeter'
		const mockPayload = { lockReason: mockLockReason, lockByWhichAdmin: mockLockByWhichAdmin }
		const mockUpdatedQuestion = { ...mockQuestion, locked: true, lockReason: mockLockReason, lockByWhichAdmin: mockLockByWhichAdmin }

		const mockUserFindOne = jest.fn().mockResolvedValue({ _id: 1 })
		const mockQuestionFindById = jest.fn().mockResolvedValueOnce(mockQuestion)
		const mockPopulate = jest.fn().mockResolvedValueOnce(mockUpdatedQuestion)
		const mockQuestionFindOneAndUpdate = jest.fn(() => ({ populate: mockPopulate }))

		jest.spyOn(User, 'findOne').mockImplementation(mockUserFindOne)
		jest.spyOn(Question, 'findById').mockImplementation(mockQuestionFindById)
		jest.spyOn(Question, 'findOneAndUpdate').mockImplementation(mockQuestionFindOneAndUpdate)

		const response = await supertest(server).post(`/question/toggleQuestionById/${mockQID}`).send(mockPayload);

		expect(response.status).toBe(200);
		expect(response.body).toEqual({ _id: mockQID, locked: true, lockReason: mockLockReason, lockByWhichAdmin: mockLockByWhichAdmin })

		expect(mockQuestionFindById).toHaveBeenCalled();
		expect(mockQuestionFindOneAndUpdate).toHaveBeenCalled();
	})

	it('can unlock a question', async () => {
		const mockQID = 1
		const mockQuestion = { _id: mockQID, locked: true, lockReason: 'This question is locked', lockByWhichAdmin: 'saltyPeter' }
		const mockPayload = { lockReason: '', lockByWhichAdmin: '' }
		const mockUpdatedQuestion = { ...mockQuestion, locked: false, lockReason: '', lockByWhichAdmin: '' }

		const mockUserFindOne = jest.fn().mockResolvedValue({ _id: 1 })
		const mockQuestionFindById = jest.fn().mockResolvedValueOnce(mockQuestion)
		const mockPopulate = jest.fn().mockResolvedValueOnce(mockUpdatedQuestion)
		const mockQuestionFindOneAndUpdate = jest.fn(() => ({ populate: mockPopulate }))

		jest.spyOn(User, 'findOne').mockImplementation(mockUserFindOne)
		jest.spyOn(Question, 'findById').mockImplementation(mockQuestionFindById)
		jest.spyOn(Question, 'findOneAndUpdate').mockImplementation(mockQuestionFindOneAndUpdate)

		const response = await supertest(server).post(`/question/toggleQuestionById/${mockQID}`).send(mockPayload);

		expect(response.status).toBe(200);
		expect(response.body).toEqual({ _id: mockQID, locked: false, lockReason: '', lockByWhichAdmin: '' })

		expect(mockQuestionFindById).toHaveBeenCalled();
		expect(mockQuestionFindOneAndUpdate).toHaveBeenCalled();
	})
})

describe('POST question/deleteQuestionById/:qid', () => {
	it('can delete question', async () => {
		const mockQID = 1
		const mockQuestion = { _id: mockQID }
		const mockUpdatedQuestion = { ...mockQuestion, status: 'deleted' }

		const mockPopulate = jest.fn().mockResolvedValueOnce(mockUpdatedQuestion)
		const mockQuestionFindOneAndUpdate = jest.fn(() => ({ populate: mockPopulate }))

		jest.spyOn(Question, 'findOneAndUpdate').mockImplementation(mockQuestionFindOneAndUpdate)

		const response = await supertest(server).post(`/question/deleteQuestionById/${mockQID}`);

		expect(response.status).toBe(200);
		expect(response.body).toEqual(mockUpdatedQuestion)
	})
})





