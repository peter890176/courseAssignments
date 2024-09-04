import { mount } from '@cypress/react';
import QuestionPage from '../../src/components/main/questionPage'
import { REACT_APP_API_URL } from '../../src/services/config'

const QUESTION_API_URL = `${REACT_APP_API_URL}/question`

describe('QuestionPage Component Test', () => {
	const mockTitleText = 'All Questions'
	const mockOrder = 'newest'
	const mockSearch = ''
	const mockSetQuestionOrder = () => { }
	const mockClickTag = () => { }
	const mockHandleAnswer = () => { }
	const mockHandleNewQuestion = () => { }
	
	const mockQList = [
		{
			title: 'test-title',
			text: 'test-text',
			asked_by: 'test-user',
			asked_by_id: 1,
			answers: [],
			tags: [{ name: 'test-tag' }],
		}
	]

	it('renders appropriately', () => {
		cy.intercept('GET', `${QUESTION_API_URL}/getQuestion?order=newest&search=`, {
			body: mockQList,
			statusCode: 200
		})

		mount(
			<QuestionPage
				title_text={mockTitleText}
				order={mockOrder}
				search={mockSearch}
				setQuestionOrder={mockSetQuestionOrder}
				clickTag={mockClickTag}
				handleAnswer={mockHandleAnswer}
				handleNewQuestion={mockHandleNewQuestion}
			/>
		)

		cy.contains('All Questions').should('be.visible')
		cy.contains('test-title').should('be.visible')
		cy.contains('test-tag').should('be.visible')
	})
})
