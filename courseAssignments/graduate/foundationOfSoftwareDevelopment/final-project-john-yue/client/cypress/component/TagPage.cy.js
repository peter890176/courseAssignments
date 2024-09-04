import { mount } from '@cypress/react';
import TagPage from '../../src/components/main/tagPage'
import { REACT_APP_API_URL } from '../../src/services/config'

const TAG_API_URL = `${REACT_APP_API_URL}/tag`

describe('TagPage Component Test', () => {
	const mockClickTag = () => {}
	const mockHandleNewQuestion = () => {}

	it('renders appropriately', () => {
		cy.intercept('GET', `${TAG_API_URL}/getTagsWithQuestionNumber`, {
			body: [{
				name: 'test-tag1',
				qcnt: 2
			},
			{
				name: 'test-tag2',
				qcnt: 1
			},
			{
				name: 'test-tag3',
				qcnt: 5
			}],
			statusCode: 200
		})

		mount(
			<TagPage
				clickTag={mockClickTag}
				handleNewQuestion={mockHandleNewQuestion}
			/>
		)

		cy.contains('3 Tags').should('be.visible')
		cy.contains('2 questions').should('be.visible')
		cy.contains('1 questions').should('be.visible')
		cy.contains('5 questions').should('be.visible')
		cy.contains('All Tags').should('be.visible')
		cy.contains('Ask a Question').should('be.visible')
	})
})
