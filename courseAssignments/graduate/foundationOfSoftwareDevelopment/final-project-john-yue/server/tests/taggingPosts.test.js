const mongoose = require("mongoose")
const supertest = require("supertest")
const { resetDB } = require('./utils')
const Tag = require('../models/tags');
const Question = require('../models/questions');

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

/*
const getTagsWithQuestionNumber = async (req, res) => {
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
        tagQuestionCount.push({ name: tag.name, qcnt: count })};
    });

    res.status(200).json(tagQuestionCount);
};

router.get("/getTagsWithQuestionNumber", getTagsWithQuestionNumber);

*/
describe('GET /getTagsWithQuestionNumber', () => {
	it('can get a list of tags with how many questions each applies to', async () => {
		const mockTags = [
			{ name: 'tag1' },
			{ name: 'tag2' },
		]

		const mockQuestions = [
			{ tags: [mockTags[0], mockTags[1]] },
			{ tags: [mockTags[0]] },
		]

		const findTagMock = jest.fn().mockResolvedValueOnce(mockTags);
		const populateMock = jest.fn().mockResolvedValueOnce(mockQuestions);
		const findQuestionMock = jest.fn().mockImplementation(() => ({ populate: populateMock }));


		jest.spyOn(Tag, 'find').mockImplementation(findTagMock);
		jest.spyOn(Question, 'find').mockImplementation(findQuestionMock);

		const response = await supertest(server).get('/tag/getTagsWithQuestionNumber');

		expect(response.status).toBe(200);
		expect(response.body).toEqual([
			{ name: 'tag1', qcnt: 2 },
			{ name: 'tag2', qcnt: 1 },
		]);
		expect(findTagMock).toHaveBeenCalled();
		expect(populateMock).toHaveBeenCalled();
		expect(findQuestionMock).toHaveBeenCalled();
	})
})
