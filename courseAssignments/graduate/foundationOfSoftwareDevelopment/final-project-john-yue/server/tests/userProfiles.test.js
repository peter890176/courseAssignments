const mongoose = require("mongoose");
const supertest = require("supertest");
const { resetDB } = require("./utils");
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

describe('POST /login', () => {
	it('can login', async () => {
		const mockUser = { username: 'saltyPeter', password: 'saltyPassword' }
		const mockUserFindOne = jest.fn().mockResolvedValueOnce(mockUser)

		jest.spyOn(User, 'findOne').mockImplementation(mockUserFindOne)

		const response = await supertest(server).post('/login').send(mockUser);

		expect(response.status).toBe(200);
	})

	it('bad username returns 404', async () => {
		const mockUser = { username: 'saltyPeter', password: 'saltyPassword' }
		const mockUserFindOne = jest.fn().mockResolvedValueOnce(null)

		jest.spyOn(User, 'findOne').mockImplementation(mockUserFindOne)

		const response = await supertest(server).post('/login').send(mockUser);

		expect(response.status).toBe(404);
	})

	it('bad password returns 401', async () => {
		const mockUser = { username: 'saltyPeter', password: 'saltyPassword' }
		const mockUserFindOne = jest.fn().mockResolvedValueOnce({ username: 'saltyPeter', password: 'wrongPassword' })

		jest.spyOn(User, 'findOne').mockImplementation(mockUserFindOne)

		const response = await supertest(server).post('/login').send(mockUser);

		expect(response.status).toBe(401);
	})
})

describe('POST /login/logout', () => {
	it('can logout', async () => {
		const response = await supertest(server).post('/login/logout');

		expect(response.status).toBe(200);
	})
})

describe('GET /login/profile', () => {
	it('can get profile', async () => {
		const response = await supertest(server).get('/login/profile');
		expect(response.status).toBe(200);
	})
})

describe('POST /login/updateProfile', () => {
	it('logged out cant update profile', async () => {
		const mockUserFindByIdAndUpdate = jest.fn()

		const response = await supertest(server).post('/login/updateProfile').send({ username: 'saltyPeter' });

		expect(response.status).toBe(401);
	})
})

describe('GET /login/username/:id', () => {
	it('can get username by id', async () => {
		const mockUserFindByID = jest.fn().mockResolvedValueOnce({ name: 'saltyPeter' })

		jest.spyOn(User, 'findById').mockImplementation(mockUserFindByID)

		const response = await supertest(server).get('/login/username/1');

		expect(response.status).toBe(200);
		expect(response.body).toEqual('saltyPeter');
	})
})

describe('POST /login/signup', () => {
	it('can signup', async () => {
		const mockUser = { username: 'saltyPeter', password: 'saltyPassword' }
		const mockUserSave = jest.fn()

		jest.spyOn(User.prototype, 'save').mockImplementation(mockUserSave)

		const response = await supertest(server).post('/login/signup').send(mockUser);

		expect(response.status).toBe(200);
	})
})
