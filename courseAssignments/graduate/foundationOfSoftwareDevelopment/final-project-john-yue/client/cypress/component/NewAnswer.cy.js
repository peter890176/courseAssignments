import { mount } from '@cypress/react';
import NewAnswer from '../../src/components/main/newAnswer'
import { REACT_APP_API_URL } from '../../src/services/config'

const ANSWER_API_URL = `${REACT_APP_API_URL}/answer`
const LOGIN_API_URL = `${REACT_APP_API_URL}/login`

describe('NewAnswer Component', () => {
	it('renders inputs and handles blank fields', () => {
		const mockQID = 1
		const handleAnswer = cy.stub()

		mount(<NewAnswer qid={mockQID} handleAnswer={handleAnswer} />);

		cy.intercept('POST', `${ANSWER_API_URL}/addAnswer`, {
			_id: 1
		})
		cy.intercept('GET', `${LOGIN_API_URL}/profile`, {
			name: 'test-user'
		})

		cy.get('#answerTextInput').should('be.visible')
		cy.contains('Post Answer').click()
		cy.contains('Answer text cannot be empty').should('be.visible')
	})

	it('can answer with non-blank field', () => {
		const mockQID = 1
		const handleAnswer = cy.stub()

		mount(<NewAnswer qid={mockQID} handleAnswer={handleAnswer} />);

		cy.intercept('POST', `${ANSWER_API_URL}/addAnswer`, {
			_id: 1
		}).as('addAnswer')
		cy.intercept('GET', `${LOGIN_API_URL}/profile`, {
			name: 'test-user'
		})

		cy.get('#answerTextInput').type('test-answer')
		cy.contains('Post Answer').click()

		cy.wait('@addAnswer').then((interception) => {
			expect(interception.request.body).to.have.property('qid', mockQID)
			expect(interception.request.body.ans).to.include({
				text: 'test-answer',
				ans_by: 'test-user',
			})
		})

		cy.wrap(handleAnswer).should('be.called')
		cy.contains('Answer text cannot be empty').should('not.exist')
	})


})


