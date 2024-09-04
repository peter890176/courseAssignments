import { mount } from '@cypress/react';
import ProfileEditor from '../../src/components/main/profilePage/ProfileEditor'
import { REACT_APP_API_URL } from '../../src/services/config'

const LOGIN_API_URL = `${REACT_APP_API_URL}/login`

describe('ProfileEditor Component', () => {
	const mockUser = {
		name: 'test-user',
		password: 'test-password',
		email: 'test-email'
	}
	const mockSetEdit = () => { }
	const mockSetUser = () => { }

	it('renders appropriately and handles blank fields', () => {
		mount(<ProfileEditor user={mockUser} setEdit={mockSetEdit} setUser={mockSetUser} />);

		cy.get('#username-edit').should('have.value', 'test-user')
		cy.get('#password-edit').should('have.value', 'test-password')
		cy.get('#email-edit').should('have.value', 'test-email')

		cy.get('#username-edit').clear()
		cy.contains('Username cannot be empty').should('be.visible')

		cy.get('#password-edit').clear()
		cy.contains('Password cannot be empty').should('be.visible')

		cy.get('#email-edit').clear()
		cy.contains('Email cannot be empty').should('be.visible')

		cy.contains('Back to Details').should('be.visible')
		cy.contains('Save').should('be.visible')
	})

	it('calls setEdit on Back to Details click', () => {
		const mockSetEdit = cy.stub()
		mount(<ProfileEditor user={mockUser} setEdit={mockSetEdit} setUser={mockSetUser} />);

		cy.contains('Back to Details').click()
		cy.wrap(mockSetEdit).should('be.called')
	})

	it('calls setUser and client function on Save click', () => {
		const mockSetUser = cy.stub()

		cy.intercept('POST', `${LOGIN_API_URL}/updateProfile`, {
			statusCode: 200,
			body: { name: 'new-username' }
		}).as('updateProfile')

		mount(<ProfileEditor user={mockUser} setEdit={mockSetEdit} setUser={mockSetUser} />);

		cy.get('#username-edit').clear().type('new-username')
		cy.get('#password-edit').clear().type('new-password')
		cy.get('#email-edit').clear().type('new-email')

		cy.contains('Save').click()

		cy.wait('@updateProfile').then((interception) => {
			cy.wrap(mockSetUser).should('be.called')
			expect(interception.request.body).to.deep.equal({
				name: 'new-username',
				password: 'new-password',
				email: 'new-email'
			})
		})
	})
})
