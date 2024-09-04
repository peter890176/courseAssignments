import { mount } from '@cypress/react';
import ProfileComponent from '../../src/components/main/profilePage/ProfileComponent'

describe('ProfileComponent', () => {
	const mockTitleText = 'Profile for test-user'
	const mockOrder = 'newest'
	const mockSetQuestionOrder = () => { }
	const mockClickTag = () => { }
	const mockHandleAnswer = () => { }
	const mockHandleNewQuestion = () => { }
	const mockHandleProfile = () => { }
	const mockUser = {
		name: 'test-user',
		email: 'test-email',
		role: 'normal',
		_id: 1
	}
	const mockSetUser = () => { }

	it('renders details screen by default', () => {
		mount(
			<ProfileComponent
				title_text={mockTitleText}
				order={mockOrder}
				setQuestionOrder={mockSetQuestionOrder}
				clickTag={mockClickTag}
				handleAnswer={mockHandleAnswer}
				handleNewQuestion={mockHandleNewQuestion}
				handleProfile={mockHandleProfile}
				user={mockUser}
				setUser={mockSetUser}
			/>
		)

		cy.contains('Profile for test-user').should('be.visible')
		cy.contains('Username: test-user').should('be.visible')
		cy.contains('Email: test-email').should('be.visible')
		cy.contains('Role: normal user').should('be.visible')
	})

	it('renders an empty div if user is null', () => {
		mount(
			<ProfileComponent
				title_text={mockTitleText}
				order={mockOrder}
				setQuestionOrder={mockSetQuestionOrder}
				clickTag={mockClickTag}
				handleAnswer={mockHandleAnswer}
				handleNewQuestion={mockHandleNewQuestion}
				handleProfile={mockHandleProfile}
				user={null}
				setUser={mockSetUser}
			/>
		)

		cy.get('div').should('be.empty')
	})
})
