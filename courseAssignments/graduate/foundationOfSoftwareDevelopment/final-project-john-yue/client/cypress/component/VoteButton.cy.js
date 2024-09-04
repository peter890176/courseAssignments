import { mount } from '@cypress/react';
import VoteButton from '../../src/components/main/answerPage/VoteButton'
import { REACT_APP_API_URL } from '../../src/services/config'

const LOGIN_API_URL = `${REACT_APP_API_URL}/login`
const QUESTIONS_API_URL = `${REACT_APP_API_URL}/questions`

describe('VoteButton Component', () => {
	it('renders buttons and handles vote', () => {
		const mockQID = 1
		const updateVote = cy.stub()
		const isLocked = false

		mount(<VoteButton questionId={mockQID} updateVote={updateVote} isLocked={isLocked} />);

		cy.intercept('POST', `${QUESTIONS_API_URL}/${mockQID}/vote`, { statusCode: 200}).as('vote')
		cy.intercept('GET', `${LOGIN_API_URL}/profile`, {
			name: 'test-user'
		})
		let stub = cy.stub()
		cy.on('window:alert', stub)

		cy.get('#up-vote').should('be.visible')
		cy.get('#down-vote').should('be.visible')
		cy.contains('Good ↑').click().then(() => {
			cy.wait('@vote').then(() => {
				expect(stub).to.have.been.calledWith('Vote successful!')
			})
		})
	})

	it('handles locked vote', () => {
		const mockQID = 1
		const updateVote = cy.stub()
		const isLocked = true

		mount(<VoteButton questionId={mockQID} updateVote={updateVote} isLocked={isLocked} />);

		cy.get('#up-vote').should('be.visible')
		cy.get('#down-vote').should('be.visible')
		cy.contains('Good ↑').should('be.disabled')
		cy.contains('Bad ↓').should('be.disabled')
	})

	it('handles unauthenticated vote', () => {
		const mockQID = 1
		const updateVote = cy.stub()
		const isLocked = false

		mount(<VoteButton questionId={mockQID} updateVote={updateVote} isLocked={isLocked} />);

		cy.intercept('GET', `${LOGIN_API_URL}/profile`, {}).as('profile')
		let stub = cy.stub()
		cy.on('window:alert', stub)

		cy.get('#up-vote').should('be.visible')
		cy.get('#down-vote').should('be.visible')
		cy.contains('Good ↑').click().then(() => {
			cy.wait('@profile').then(() => {
				expect(stub.getCall(0)).to.be.calledWith('Please login to vote.')
			})
		})
	})

	it('handles duplicate vote', () => {
		const mockQID = 1
		const updateVote = cy.stub()
		const isLocked = false

		mount(<VoteButton questionId={mockQID} updateVote={updateVote} isLocked={isLocked} />);

		cy.intercept('POST', `${QUESTIONS_API_URL}/${mockQID}/vote`, {
			body: { duplicate: true }
		}).as('vote')
		cy.intercept('GET', `${LOGIN_API_URL}/profile`, {
			name: 'test-user'
		})
		let stub = cy.stub()
		cy.on('window:alert', stub)

		cy.get('#up-vote').should('be.visible')
		cy.get('#down-vote').should('be.visible')
		cy.contains('Good ↑').click().then(() => {
			cy.wait('@vote').then(() => {
				expect(stub.getCall(0)).to.be.calledWith('You have already voted.')
			})
		})
	})

	it('handles cancelled vote', () => {
		const mockQID = 1
		const updateVote = cy.stub()
		const isLocked = false

		mount(<VoteButton questionId={mockQID} updateVote={updateVote} isLocked={isLocked} />);

		cy.intercept('POST', `${QUESTIONS_API_URL}/${mockQID}/vote`, {
			body: { cancel: true }
		}).as('vote')
		cy.intercept('GET', `${LOGIN_API_URL}/profile`, {
			name: 'test-user'
		})
		let stub = cy.stub()
		cy.on('window:alert', stub)

		cy.get('#up-vote').should('be.visible')
		cy.get('#down-vote').should('be.visible')
		cy.contains('Good ↑').click().then(() => {
			cy.wait('@vote').then(() => {
				expect(stub.getCall(0)).to.be.calledWith('vote cancelled')
			})
		})
	})
})
