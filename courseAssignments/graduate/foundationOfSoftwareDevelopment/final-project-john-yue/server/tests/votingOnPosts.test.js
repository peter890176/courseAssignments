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

describe('POST /questions/:qid/vote', () => {
	it('can upvote a question', async () => {
		const mockQID = 1
		const mockPayload = { username: 'saltyPeter', vote: 'up' }

		const mockUserFindOne = jest.fn().mockResolvedValueOnce({ username: mockPayload.username })
		const mockQuestionFindById = jest.fn().mockResolvedValueOnce({ _id: mockQID, votes: 0, votedUsers: [] })
		const mockQuestionFindOneAndUpdate = jest.fn().mockResolvedValueOnce({ _id: mockQID, votes: 1, votedUsers: [mockPayload.username] })

		jest.spyOn(User, 'findOne').mockImplementation(mockUserFindOne)
		jest.spyOn(Question, 'findById').mockImplementation(mockQuestionFindById)
		jest.spyOn(Question, 'findOneAndUpdate').mockImplementation(mockQuestionFindOneAndUpdate)

		const response = await supertest(server).post(`/questions/${mockQID}/vote`).send(mockPayload);

		expect(response.status).toBe(200);
		expect(response.body).toEqual({ votes: 1})
		expect(mockUserFindOne).toHaveBeenCalled();
		expect(mockQuestionFindById).toHaveBeenCalled();
		expect(mockQuestionFindOneAndUpdate).toHaveBeenCalled();
	})

	it('can downvote a question', async () => {
		const mockQID = 1
		const mockPayload = { username: 'saltyPeter', vote: 'down' }

		const mockUserFindOne = jest.fn().mockResolvedValueOnce({ username: mockPayload.username })
		const mockQuestionFindById = jest.fn().mockResolvedValueOnce({ _id: mockQID, votes: 0, votedUsers: [] })
		const mockQuestionFindOneAndUpdate = jest.fn().mockResolvedValueOnce({ _id: mockQID, votes: -1, votedUsers: [mockPayload.username] })

		jest.spyOn(User, 'findOne').mockImplementation(mockUserFindOne)
		jest.spyOn(Question, 'findById').mockImplementation(mockQuestionFindById)
		jest.spyOn(Question, 'findOneAndUpdate').mockImplementation(mockQuestionFindOneAndUpdate)

		const response = await supertest(server).post(`/questions/${mockQID}/vote`).send(mockPayload);

		expect(response.status).toBe(200);
		expect(response.body).toEqual({ votes: -1})
		expect(mockUserFindOne).toHaveBeenCalled();
		expect(mockQuestionFindById).toHaveBeenCalled();
		expect(mockQuestionFindOneAndUpdate).toHaveBeenCalled();
	})

	it('voting twice removes vote', async () => {
		const mockQID = 1
		const mockPayload = { username: 'saltyPeter', vote: 'up' }

		const mockUserFindOne = jest.fn().mockResolvedValueOnce({ username: mockPayload.username, _id: 1})
		const mockQuestionFindById = jest.fn().mockResolvedValueOnce({ _id: mockQID, votes: 1, votedUsers: [{
			id: 1,
			voteType: 'up'
		}] })
		const mockQuestionFindOneAndUpdate = jest.fn().mockResolvedValueOnce({ _id: mockQID, votes: 0, votedUsers: [] })

		jest.spyOn(User, 'findOne').mockImplementation(mockUserFindOne)
		jest.spyOn(Question, 'findById').mockImplementation(mockQuestionFindById)
		jest.spyOn(Question, 'findOneAndUpdate').mockImplementation(mockQuestionFindOneAndUpdate)

		const response = await supertest(server).post(`/questions/${mockQID}/vote`).send(mockPayload);

		expect(response.status).toBe(200);
		expect(response.body).toEqual({ votes: 1, duplicate: true, cancel: false})
		expect(mockUserFindOne).toHaveBeenCalled();
		expect(mockQuestionFindById).toHaveBeenCalled();
		expect(mockQuestionFindOneAndUpdate).toHaveBeenCalledTimes(0);
	})
})
