import { mount } from '@cypress/react';
import ProfilePage from '../../src/components/main/profilePage'
import { REACT_APP_API_URL } from '../../src/services/config'

const LOGIN_API_URL = `${REACT_APP_API_URL}/login`

describe('ProfilePage Component', () => {
	const mockTitleText = 'Profile for test-user'
	const mockOrder = 'newest'
	const mockSetQuestionOrder = () => { }
	const mockClickTag = () => { }
	const mockHandleAnswer = () => { }
	const mockHandleNewQuestion = () => { }
	const mockHandleProfile = () => { }
	
	it('renders LoginPage component if user is null', () => {
		cy.intercept('GET', `${LOGIN_API_URL}/profile`, {
			body: null,
			statusCode: 200
		})

		mount(
			<ProfilePage
				title_text={mockTitleText}
				order={mockOrder}
				setQuestionOrder={mockSetQuestionOrder}
				clickTag={mockClickTag}
				handleAnswer={mockHandleAnswer}
				handleNewQuestion={mockHandleNewQuestion}
				handleProfile={mockHandleProfile}
			/>
		)

		cy.contains('Login').should('be.visible')
	})
})
