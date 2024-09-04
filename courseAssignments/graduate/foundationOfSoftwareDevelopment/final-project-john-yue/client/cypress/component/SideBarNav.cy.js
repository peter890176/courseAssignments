import { mount } from '@cypress/react';
import SideBarNav from '../../src/components/main/sideBarNav'

describe('SideBarNav Component', () => {
	it('display text correctly', () => {
		const mockSelected = 'q'
		const mockHandleQuestions = () => {}
		const mockHandleTags = () => {}
		const mockHandleProfile = () => {}

		mount(<SideBarNav selected={mockSelected} handleQuestions={mockHandleQuestions} handleTags={mockHandleTags} handleProfile={mockHandleProfile} />);

		cy.contains('Questions').should('be.visible')
		cy.contains('Tags').should('be.visible')
		cy.contains('User Profile').should('be.visible')
	})

	it('calls handleQuestions, handleTags, and handleProfile with correct arguments', () => {
		const mockSelected = 'q'
		const mockHandleQuestions = cy.stub()
		const mockHandleTags = cy.stub()
		const mockHandleProfile = cy.stub()

		mount(<SideBarNav selected={mockSelected} handleQuestions={mockHandleQuestions} handleTags={mockHandleTags} handleProfile={mockHandleProfile} />);

		cy.get('#menu_question').click()
		cy.get('#menu_tag').click()
		cy.get('#menu_user').click()

		cy.wrap(mockHandleQuestions).should('be.called')
		cy.wrap(mockHandleTags).should('be.called')
		cy.wrap(mockHandleProfile).should('be.called')
	})

	it('highlights selected menu button q', () => {
		const mockSelected = 'q'
		const mockHandleQuestions = () => {}
		const mockHandleTags = () => {}
		const mockHandleProfile = () => {}

		mount(<SideBarNav selected={mockSelected} handleQuestions={mockHandleQuestions} handleTags={mockHandleTags} handleProfile={mockHandleProfile} />);

		cy.get('#menu_question').should('have.class', 'menu_selected')
		cy.get('#menu_tag').should('not.have.class', 'menu_selected')
		cy.get('#menu_user').should('not.have.class', 'menu_selected')
	})

	it('highlights selected menu button t', () => {
		const mockSelected = 't'
		const mockHandleQuestions = () => {}
		const mockHandleTags = () => {}
		const mockHandleProfile = () => {}

		mount(<SideBarNav selected={mockSelected} handleQuestions={mockHandleQuestions} handleTags={mockHandleTags} handleProfile={mockHandleProfile} />);

		cy.get('#menu_question').should('not.have.class', 'menu_selected')
		cy.get('#menu_tag').should('have.class', 'menu_selected')
		cy.get('#menu_user').should('not.have.class', 'menu_selected')
	})
})
