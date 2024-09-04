import React from 'react';
import { mount } from '@cypress/react';
import Input from '../../src/components/main/baseComponents/input';

describe('Input Component', () => {
    it('renders the component', () => {
        mount(<Input type="text" title="Test Title" id="test-input" />);
        cy.get('.input_title').should('contain', 'Test Title');
    });

    it('shows mandatory asterisk when mandatory is true', () => {
        mount(<Input type="text" title="Mandatory Input" id="mandatory-input" mandatory={true} />);
        cy.get('.input_title').should('contain', '*');
    });

    it('does not show mandatory asterisk when mandatory is false', () => {
        mount(<Input type="text" title="Optional Input" id="optional-input" mandatory={false} />);
        cy.get('.input_title').should('not.contain', '*');
    });

    it('displays hint when provided', () => {
        mount(<Input type="text" hint="Enter your name" id="hint-input" />);
        cy.get('.input_hint').should('contain', 'Enter your name');
    });

    it('accepts input and triggers state update', () => {
        const setState = cy.stub().as('setState');
        mount(<Input type="text" id="update-input" val="" setState={setState} />);
        cy.get('#update-input').type('Hello, World!');
        cy.get('@setState').should('have.been.called');
    });

    it('displays error message when error is provided', () => {
        mount(<Input type="text" id="error-input" err="Error message" />);
        cy.get('.input_error').should('contain', 'Error message');
    });
});
