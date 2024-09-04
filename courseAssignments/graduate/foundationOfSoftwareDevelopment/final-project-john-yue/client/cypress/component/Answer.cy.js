import { mount } from '@cypress/react';
import Answer from '../../src/components/main/answerPage/answer'
import { REACT_APP_API_URL } from "../../src/services/config";

const LOGIN_API_URL = `${REACT_APP_API_URL}/login`;

describe('Answer Component', () => {
	it('renders text correctly', () => {
		const testText = 'test-text';
		mount(<Answer text={testText} ansBy='test-author' meta='test-meta' />);
		cy.get('#answerText').should('have.text', testText);
	})

	it('displays author and meta correctly', () => {
		const author = 'test-author';
		const meta = 'test-meta';
		cy.intercept('GET', `${LOGIN_API_URL}/username/1`, { body: "test-author" })
		mount(<Answer text='test-text' ansBy={author} meta={meta} authorId={1} />);
		cy.get('.answer_author').should('have.text', author);
		cy.get('.answer_question_meta').should('have.text', meta);
	})
})
