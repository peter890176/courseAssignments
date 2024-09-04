import { mount } from '@cypress/react';
import NewQuestion from '../../src/components/main/newQuestion'
import { REACT_APP_API_URL } from '../../src/services/config'

const QUESTION_API_URL = `${REACT_APP_API_URL}/question`
const LOGIN_API_URL = `${REACT_APP_API_URL}/login`

describe('NewQuestion Component', () => {
	it('renders inputs and handles blank fields', () => {
		const handleQuestions = cy.stub()

		mount(<NewQuestion handleQuestions={handleQuestions} />);

		cy.intercept('GET', `${LOGIN_API_URL}/profile`, {
			name: 'test-user'
		})

		cy.get('#formTitleInput').should('be.visible')
		cy.get('#formTextInput').should('be.visible')
		cy.get('#formTagInput').should('be.visible')
		cy.contains('Post Question').click()

		cy.contains('Title cannot be empty').should('be.visible')
		cy.contains('Question text cannot be empty').should('be.visible')
		cy.contains('Should have at least 1 tag').should('be.visible')
	})

	it('can post question with non-blank fields', () => {
		const handleQuestions = cy.stub()

		mount(<NewQuestion handleQuestions={handleQuestions} />);

		cy.intercept('POST', `${QUESTION_API_URL}/addQuestion`, {
			_id: 1
		}).as('addQuestion')

		cy.intercept('GET', `${LOGIN_API_URL}/profile`, {
			name: 'test-user'
		})

		cy.get('#formTitleInput').type('test-title')
		cy.get('#formTextInput').type('test-text')
		cy.get('#formTagInput').type('test-tag')
		cy.contains('Post Question').click()

		cy.wait('@addQuestion').then((interception) => {
			expect(interception.request.body).to.include({
				title: 'test-title',
				text: 'test-text',
				asked_by: 'test-user',
			})
			expect(interception.request.body.tags).to.deep.equal(['test-tag'])
		})

		cy.wrap(handleQuestions).should('be.called')
		cy.contains('Title cannot be empty').should('not.exist')
		cy.contains('Question text cannot be empty').should('not.exist')
		cy.contains('Should have at least 1 tag').should('not.exist')
	})
})
