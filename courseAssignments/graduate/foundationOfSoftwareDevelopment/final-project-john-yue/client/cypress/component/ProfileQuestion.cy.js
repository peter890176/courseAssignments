import { mount } from '@cypress/react';
import ProfileQuestion from '../../src/components/main/profilePage/profile'
import { REACT_APP_API_URL } from '../../src/services/config'

const LOGIN_API_URL = `${REACT_APP_API_URL}/login`

describe('ProfileQuestion Component', () => {
	it('renders inputs correctly', () => {
		const mockQuestion = {
			_id: 1,
			title: 'test-title',
			votes: 3,
			views: 5,
			tags: [{ name: 'test-tag' }],
			asked_by: 'test-user',
			asked_by_id: 1,
			answers: [{ _id: 1 }],
			ask_date_time: '2021-10-10T00:00:00.000Z'
		}
		const mockClickTag = cy.stub()
		const mockHandleAnswer = cy.stub()

		const url = `${LOGIN_API_URL}/username/1`
		cy.intercept('GET', url, {
			body: 'test-user'
		}).as('getUsername')

		mount(<ProfileQuestion q={mockQuestion} handleAnswer={mockHandleAnswer} clickTag={mockClickTag} />);

		cy.wait('@getUsername').then((interception) => {
			console.log(`interception.request.url: ${interception.request.url}`)
		})

		cy.contains('test-title').should('exist')
		cy.contains('test-tag').should('exist')
		cy.contains('test-user').should('exist')
		cy.contains('3 votes').should('exist')
		cy.contains('5 views').should('exist')
		cy.contains('1 answer').should('exist')

		cy.get('.question').click()

		cy.wrap(mockHandleAnswer).should('be.called')

		cy.contains('test-tag').click()

		cy.wrap(mockClickTag).should('be.called')
	})
})
