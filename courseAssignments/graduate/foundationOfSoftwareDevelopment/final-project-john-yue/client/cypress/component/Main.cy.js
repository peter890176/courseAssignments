import { mount } from '@cypress/react';
import Main from '../../src/components/main'

describe('Main Component', () => {
	const mockSearch = ''
	const mockTitle = 'All Questions'
	const mockSetQuestionPage = () => { }

	it('renders question page with all questions by default', () => {
		mount(
			<Main
				search={mockSearch}
				title={mockTitle}
				setQuestionPage={mockSetQuestionPage}
			/>
		)

		cy.contains('All Questions').should('be.visible')
	})

	it('alert when try to Ask a Question but not logged in', () => {
		mount(
			<Main
				search={mockSearch}
				title={mockTitle}
				setQuestionPage={mockSetQuestionPage}
			/>
		)

		cy.on('window:alert', (str) => {
			expect(str).to.equal('Please login first.')
		})

		cy.contains('Ask a Question').click()
	})
})
