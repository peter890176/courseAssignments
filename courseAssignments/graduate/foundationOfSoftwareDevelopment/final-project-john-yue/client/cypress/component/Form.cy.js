import { mount } from '@cypress/react';
import Form from '../../src/components/main/baseComponents/form'

describe('Form Component', () => {
	it('render children', () => {
		const childContent = <p>Hello, world!</p>;
		mount(<Form>{childContent}</Form>);
		cy.get('.form').children().should('contain.text', 'Hello, world!')
	})
})
