import { mount } from '@cypress/react';
import Header from '../../src/components/header'

describe('Header component', () => {
	it('render correctly with initial search value', () => {
		  const initialSearchValue = 'test-search';
		  const mockSetQuestionPage = () => {}
		  mount(<Header search={initialSearchValue} setQuestionPage={mockSetQuestionPage} />);

		  cy.get('#searchBar').should('have.value', initialSearchValue);
	})

	it('updates input value on user input', () => {
		const initialSearchValue = 'test-search';
		const mockSetQuestionPage = () => {}
		mount(<Header search={initialSearchValue} setQuestionPage={mockSetQuestionPage} />);

		const newSearchValue = 'new-search';
		cy.get('#searchBar').clear().type(newSearchValue).should('have.value', newSearchValue);
	})

	it('calls setQuestionPage with correct value on Enter key press', () => {
		const initialSearchValue = 'test-search';
		const mockSetQuestionPage = cy.stub().as('setQuestionPage');
		mount(<Header search={initialSearchValue} setQuestionPage={mockSetQuestionPage} />);

		const newSearchValue = 'new-search';
		cy.get('#searchBar').clear().type(newSearchValue).type('{enter}').then(() => {
			expect(mockSetQuestionPage).to.be.calledWith(newSearchValue, 'Search Results');
		})
	})
})
