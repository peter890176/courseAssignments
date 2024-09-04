import { mount } from '@cypress/react';
import QuestionBody from '../../src/components/main/answerPage/questionBody'
import { REACT_APP_API_URL } from "../../src/services/config";

const LOGIN_API_URL = `${REACT_APP_API_URL}/login`;

describe('QuestionBody Component', () => {
	const props = {
		views: '500',
		text: 'some text with <script>scripts</script> and [links](http://example.com).',
		askby: 'Jane Doe',
		votes: '25',
		meta: '2 days ago',
		authorId: '1'
	}
	const expectedHTML = '<div>some text with &lt;script&gt;scripts&lt;/script&gt; and <a href="http://example.com" target="_blank" rel="noopener noreferrer">links</a>.</div>'

	it('display views and votes correctly', () => {
		mount(<QuestionBody {...props} />);
		cy.contains(`${props.views} views`).should('be.visible');
		cy.contains(`${props.votes} votes`).should('be.visible');
	})

	it('encodes and processes the text correctly', () => {
		mount(<QuestionBody {...props} />);
		cy.get('.answer_question_text').should('have.html', expectedHTML);
	})

	it('renders author and meta correctly', () => {
		cy.intercept('GET', `${LOGIN_API_URL}/username/1`, { body: props.askby })
		mount(<QuestionBody {...props} />);
		cy.get('.question_author').should('have.text', props.askby);
		cy.get('.answer_question_meta').should('have.text', `asked ${props.meta}`);
	})
})
