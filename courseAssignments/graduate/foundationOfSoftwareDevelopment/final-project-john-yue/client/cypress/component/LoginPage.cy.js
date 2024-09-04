import { mount } from '@cypress/react';
import LoginPage from '../../src/components/main/loginPage'
import * as loginService from '../../src/services/loginService'
import { REACT_APP_API_URL } from '../../src/services/config'

const LOGIN_API_URL = `${REACT_APP_API_URL}/login`

describe('LoginPage Component', () => {
	it('renders inputs and handles blank fields', () => {
		const handleProfile = cy.stub()
		mount(<LoginPage handleProfile={handleProfile} />);
		cy.get('button').contains('Login').click()
		cy.get('#loginUsernameInput').parent().should('contain', 'Username cannot be empty')
		cy.get('#loginPasswordInput').parent().should('contain', 'Password cannot be empty')
	})

	it('successful login', () => {
		const handleProfile = cy.stub()
		mount(<LoginPage handleProfile={handleProfile} />);

		cy.intercept('POST', LOGIN_API_URL, { statusCode: 200 }).as('login')

		cy.get('#loginUsernameInput').type('test-username')
		cy.get('#loginPasswordInput').type('test-password')
		cy.get('button').contains('Login').click()

		cy.wait('@login').then((interception) => {

			expect(interception.request.body).to.deep.equal({
				username: 'test-username',
				password: 'test-password'
			})
		})
		cy.wrap(handleProfile).should('be.called')
	})

	it('unsuccesful login', () => {
		const handleProfile = cy.stub()
		mount(<LoginPage handleProfile={handleProfile} />);

		cy.intercept('POST', LOGIN_API_URL, { statusCode: 404 }).as('login')

		cy.get('#loginUsernameInput').type('test-username')
		cy.get('#loginPasswordInput').type('test-password')
		cy.get('button').contains('Login').click()

		cy.wait('@login').then((interception) => {
			expect(interception.request.body).to.deep.equal({
				username: 'test-username',
				password: 'test-password'
			})
		})
		cy.contains('Invalid username or password').should('be.visible')
	})
	
	it('successful signup', () => {
		const handleProfile = cy.stub()
		mount(<LoginPage handleProfile={handleProfile} />);

		cy.intercept('POST', `${LOGIN_API_URL}/signup`, { statusCode: 200 }).as('signup')

		cy.get('#loginUsernameInput').type('test-username')
		cy.get('#loginPasswordInput').type('test-password')
		cy.get('button').contains('Signup').click()

		cy.wait('@signup').then((interception) => {
			expect(interception.request.body).to.deep.equal({
				username: 'test-username',
				password: 'test-password'
			})
		})

		cy.contains('Username already taken').should('not.exist')
	})

	it('unsuccesful signup', () => {
		const handleProfile = cy.stub()
		mount(<LoginPage handleProfile={handleProfile} />);

		cy.intercept('POST', `${LOGIN_API_URL}/signup`, { statusCode: 404 }).as('signup')

		cy.get('#loginUsernameInput').type('test-username')
		cy.get('#loginPasswordInput').type('test-password')
		cy.get('button').contains('Signup').click()

		cy.wait('@signup').then((interception) => {
			expect(interception.request.body).to.deep.equal({
				username: 'test-username',
				password: 'test-password'
			})
		})

		cy.contains('Username already taken').should('be.visible')
	})
})
