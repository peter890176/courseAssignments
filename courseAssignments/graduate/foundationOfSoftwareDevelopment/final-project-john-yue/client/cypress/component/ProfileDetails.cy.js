import { mount } from '@cypress/react';
import ProfileDetails from '../../src/components/main/profilePage/ProfileDetails'
import { REACT_APP_API_URL } from '../../src/services/config'

const QUESTION_API_URL = `${REACT_APP_API_URL}/question`
const LOGIN_API_URL = `${REACT_APP_API_URL}/login`

describe('ProfileDetails Component', () => {
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
	const mockSetEdit = () => { }

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
		cy.intercept('GET', `${QUESTION_API_URL}/getQuestionByUserFilter/1`, {
			body: mockQList,
			statusCode: 200
		})

		mount(
			<ProfileDetails
				title_text={mockTitleText}
				order={mockOrder}
				setQuestionOrder={mockSetQuestionOrder}
				clickTag={mockClickTag}
				handleAnswer={mockHandleAnswer}
				handleNewQuestion={mockHandleNewQuestion}
				handleProfile={mockHandleProfile}
				user={mockUser}
				setUser={mockSetUser}
				setEdit={mockSetEdit}
			/>
		)

		cy.contains('Profile for test-user').should('be.visible')
		cy.contains('Username: test-user').should('be.visible')
		cy.contains('Email: test-email').should('be.visible')
		cy.contains('Role: normal user').should('be.visible')
		cy.contains("test-user's questions: 1").should('be.visible')
	})

	it('calls logout on client when logout button is clicked', () => {
		const mockSetUser = cy.stub()

		cy.intercept('GET', `${QUESTION_API_URL}/getQuestionByUserFilter/1`, {
			body: mockQList,
			statusCode: 200
		}).as('getQuestionByUserFilter')

		cy.intercept('POST', `${LOGIN_API_URL}/logout`, {
			statusCode: 200
		}).as('logout')

		mount(
			<ProfileDetails
				title_text={mockTitleText}
				order={mockOrder}
				setQuestionOrder={mockSetQuestionOrder}
				clickTag={mockClickTag}
				handleAnswer={mockHandleAnswer}
				handleNewQuestion={mockHandleNewQuestion}
				handleProfile={mockHandleProfile}
				user={mockUser}
				setUser={mockSetUser}
				setEdit={mockSetEdit}
			/>
		)

		cy.wait('@getQuestionByUserFilter')
		cy.contains('Logout').click()

		cy.wait('@logout').then(() => {
			cy.wrap(mockSetUser).should('be.called')
		})
	})

	it('calls setEdit when Edit Profile button is clicked', () => {
		const mockSetEdit = cy.stub()

		cy.intercept('GET', `${QUESTION_API_URL}/getQuestionByUserFilter/1`, {
			body: mockQList,
			statusCode: 200
		}).as('getQuestionByUserFilter')

		mount(
			<ProfileDetails
				title_text={mockTitleText}
				order={mockOrder}
				setQuestionOrder={mockSetQuestionOrder}
				clickTag={mockClickTag}
				handleAnswer={mockHandleAnswer}
				handleNewQuestion={mockHandleNewQuestion}
				handleProfile={mockHandleProfile}
				user={mockUser}
				setUser={mockSetUser}
				setEdit={mockSetEdit}
			/>
		)

		cy.wait('@getQuestionByUserFilter')
		cy.contains('Edit Profile').click()

		cy.wrap(mockSetEdit).should('be.called')
	})
})
