import { mount } from '@cypress/react';
import Textarea from '../../src/components/main/baseComponents/textarea'

describe('Textarea Component', () => {
    it('renders the component', () => {
        mount(<Textarea title="Test Title" id="test-textarea" />);
        cy.get('.input_title').should('contain', 'Test Title');
    });

    it('shows mandatory asterisk when mandatory is true', () => {
        mount(<Textarea title="Mandatory Textarea" id="mandatory-textarea" mandatory={true} />);
        cy.get('.input_title').should('contain', '*');
    });

    it('does not show mandatory asterisk when mandatory is false', () => {
        mount(<Textarea title="Optional Textarea" id="optional-textarea" mandatory={false} />);
        cy.get('.input_title').should('not.contain', '*');
    });

    it('displays hint when provided', () => {
        mount(<Textarea title="Hint Textarea" hint="Provide additional details" id="hint-textarea" />);
        cy.get('.input_hint').should('contain', 'Provide additional details');
    });

    it('accepts input and triggers state update', () => {
        const setState = cy.stub().as('setState');
        mount(<Textarea title="Update Textarea" id="update-textarea" val="" setState={setState} />);
        cy.get('#update-textarea').type('Hello, World!');
        cy.get('@setState').should('have.been.called');
    });

    it('displays error message when error is provided', () => {
        mount(<Textarea title="Error Textarea" id="error-textarea" err="Error message" />);
        cy.get('.input_error').should('contain', 'Error message');
    });
});
