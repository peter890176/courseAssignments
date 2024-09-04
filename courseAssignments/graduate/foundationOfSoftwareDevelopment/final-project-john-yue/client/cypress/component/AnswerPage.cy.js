import { mount } from '@cypress/react';
import AnswerPage from '../../src/components/main/answerPage'
import { REACT_APP_API_URL } from '../../src/services/config'

const ANSWER_API_URL = `${REACT_APP_API_URL}/answer`
const LOGIN_API_URL = `${REACT_APP_API_URL}/login`
const QUESTION_API_URL = `${REACT_APP_API_URL}/question`

describe('AnswerPage Component', () => {
	const mockQID = 1
	const mockUnlockedQuestion = {
		title: 'test-title',
		text: 'test-text',
		asked_by: 'test-user',
		asked_by_id: 1,
		answers: [
			{
				text: 'test-answer',
				ans_by: 'test-user',
				ans_by_id: 1,
				ans_date_time: new Date().toISOString(),
			}
		],
		lock: 'unlocked',
		views: 10,
		votes: 5,
		ask_date_time: new Date().toISOString(),
	}
	const mockLockedQuestion = {
		title: 'test-title',
		text: 'test-text',
		asked_by: 'test-user',
		asked_by_id: 1,
		answers: [],
		lock: 'locked',
		views: 15,
		votes: 7,
		ask_date_time: new Date().toISOString(),
		lockReason: 'test-reason',
		lockedBy: 'test-admin',
		lockByWhichAdminId: '1'
	}

	let mockHandleNewQuestion = () => {}
	let mockHandleNewAnswer = () => {}

	it('renders question for non-admin user', () => {
		cy.intercept('GET', `${QUESTION_API_URL}/getQuestionById/${mockQID}`, {
			body: mockUnlockedQuestion,
			statusCode: 200
		})

		cy.intercept('GET', `${LOGIN_API_URL}/profile`, {
			body: {
				name: 'test-user',
				role: 'normal'
			},
			statusCode: 200
		})

		cy.intercept('GET', `${LOGIN_API_URL}/username/1`, { body: "test-user" })

		mount(<AnswerPage qid={mockQID} handleNewQuestion={mockHandleNewQuestion} handleNewAnswer={mockHandleNewAnswer} />);

		cy.contains('test-title').should('be.visible')
		cy.contains('test-text').should('be.visible')
		cy.contains('test-user').should('be.visible')
		cy.contains('10 views').should('be.visible')
		cy.contains('5 votes').should('be.visible')
		cy.contains('test-answer').should('be.visible')

		cy.contains('Ask a Question').should('be.visible')
		cy.contains('Good ↑').should('be.visible')
		cy.contains('Bad ↓').should('be.visible')
		cy.contains('Answer Question').should('be.visible')

		cy.contains('Delete Question').should('not.exist')
		cy.contains('Lock Question').should('not.exist')
	})
	
	it('renders question for admin user', () => {
		cy.intercept('GET', `${QUESTION_API_URL}/getQuestionById/${mockQID}`, {
			body: mockUnlockedQuestion,
			statusCode: 200
		})

		cy.intercept('GET', `${LOGIN_API_URL}/profile`, {
			body: {
				name: 'test-user',
				role: 'admin'
			},
			statusCode: 200
		})
		
		cy.intercept('GET', `${LOGIN_API_URL}/username/1`, { body: "test-user" })

		mount(<AnswerPage qid={mockQID} handleNewQuestion={mockHandleNewQuestion} handleNewAnswer={mockHandleNewAnswer} />);

		cy.contains('test-title').should('be.visible')
		cy.contains('test-text').should('be.visible')
		cy.contains('test-user').should('be.visible')
		cy.contains('10 views').should('be.visible')
		cy.contains('5 votes').should('be.visible')
		cy.contains('test-answer').should('be.visible')

		cy.contains('Ask a Question').should('be.visible')
		cy.contains('Good ↑').should('be.visible')
		cy.contains('Bad ↓').should('be.visible')
		cy.contains('Answer Question').should('be.visible')

		cy.contains('Delete Question').should('be.visible')
		cy.contains('Lock Question').should('be.visible')
	})

	it('calls handleNewQuestion when Ask Question clicked', () => {
		mockHandleNewQuestion = cy.stub()

		cy.intercept('GET', `${QUESTION_API_URL}/getQuestionById/${mockQID}`, {
			body: mockUnlockedQuestion,
			statusCode: 200
		})

		cy.intercept('GET', `${LOGIN_API_URL}/profile`, {
			body: {
				name: 'test-user',
				role: 'admin'
			},
			statusCode: 200
		})

		mount(<AnswerPage qid={mockQID} handleNewQuestion={mockHandleNewQuestion} handleNewAnswer={mockHandleNewAnswer} />);

		cy.contains('Ask a Question').click().then(() => {
			expect(mockHandleNewQuestion).to.be.called
		})
	})
	
	it('calls handleNewAnswer when Answer Question clicked', () => {
		mockHandleNewAnswer = cy.stub()

		cy.intercept('GET', `${QUESTION_API_URL}/getQuestionById/${mockQID}`, {
			body: mockUnlockedQuestion,
			statusCode: 200
		})

		cy.intercept('GET', `${LOGIN_API_URL}/profile`, {
			body: {
				name: 'test-user',
				role: 'admin'
			},
			statusCode: 200
		})

		mount(<AnswerPage qid={mockQID} handleNewQuestion={mockHandleNewQuestion} handleNewAnswer={mockHandleNewAnswer} />);

		cy.contains('Answer Question').click().then(() => {
			expect(mockHandleNewAnswer).to.be.called
		})
	})
})
