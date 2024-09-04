const mongoose = require("mongoose");
const supertest = require("supertest");
const Question = require("../models/questions");
const { resetDB, getMockQuestionsWithPopulatedAnswersAndTags } = require("./utils");

let server
let mockQuestionsWithPopulatedAnswersAndTags

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


describe('GET /getQuestion', () => {
	it('can get questions in active order', async () => {
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
			mockQuestionsWithPopulatedAnswersAndTags[2].title,
			mockQuestionsWithPopulatedAnswersAndTags[0].title,
			mockQuestionsWithPopulatedAnswersAndTags[1].title
		]

		jest.spyOn(Question, 'find').mockImplementation(findMock);
		const response = await supertest(server).get('/question/getQuestion?order=active&search=');
		expect(response.status).toBe(200);
		expect(response.body.map(q => q.title)).toEqual(orderedResult);
		expect(findMock).toHaveBeenCalled();
	})

	it('can get unanswered questions', async () => {
		mockQuestionsWithPopulatedAnswersAndTags = await getMockQuestionsWithPopulatedAnswersAndTags()
		const noDeletedQuestions = mockQuestionsWithPopulatedAnswersAndTags.slice(0, -1)
		const findMock = jest.fn().mockImplementation(() => (
			{populate: jest.fn().mockImplementation(() => (
				{populate: jest.fn().mockResolvedValueOnce(
					noDeletedQuestions
				)})
			)}
		));

		const orderedResult = []

		jest.spyOn(Question, 'find').mockImplementation(findMock);
		const response = await supertest(server).get('/question/getQuestion?order=unanswered&search=');
		expect(response.status).toBe(200);
		expect(response.body.map(q => q.title)).toEqual(orderedResult);
		expect(findMock).toHaveBeenCalled();
	});

	it('can search by keyword', async () => {
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
			mockQuestionsWithPopulatedAnswersAndTags[1].title
		]

		jest.spyOn(Question, 'find').mockImplementation(findMock);
		const response = await supertest(server).get('/question/getQuestion?order=newest&search=storage');
		expect(response.status).toBe(200);
		expect(response.body.map(q => q.title)).toEqual(orderedResult);
		expect(findMock).toHaveBeenCalled();
	});

	it('can search by tag', async () => {
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
			mockQuestionsWithPopulatedAnswersAndTags[0].title,
			mockQuestionsWithPopulatedAnswersAndTags[2].title
		]

		jest.spyOn(Question, 'find').mockImplementation(findMock);
		const response = await supertest(server).get('/question/getQuestion?order=newest&search=[javascript]');

		expect(response.status).toBe(200);
		expect(response.body.map(q => q.title)).toEqual(orderedResult);
		expect(findMock).toHaveBeenCalled();
	});

	it('can search by tag and keyword', async () => {
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

		jest.spyOn(Question, 'find').mockImplementation(findMock);
		const response = await supertest(server).get('/question/getQuestion?order=newest&search=[javascript] storage');

		expect(response.status).toBe(200);
		expect(response.body.map(q => q.title)).toEqual(orderedResult);
		expect(findMock).toHaveBeenCalled();
	});
})
