import { mount } from '@cypress/react';
import AnswerHeader from '../../src/components/main/answerPage/header'

describe('AnswerHeader Component', () => {
	const ansCount = "5";
	const title = "test-title";
	const mockHandleNewQuestion = () => {};

	it('display answer count and title correctly', () => {
		mount(<AnswerHeader ansCount={ansCount} title={title} handleNewQuestion={mockHandleNewQuestion} />);
		cy.contains(`${ansCount} answers`).should('be.visible');
		cy.contains(title).should('be.visible');
	})

	it('render the Ask a Question button', () => {
		mount(<AnswerHeader ansCount={ansCount} title={title} handleNewQuestion={mockHandleNewQuestion} />);
		cy.contains('Ask a Question').should('be.visible');
	})

	it('calls handleNewQuestion on button click', () => {
		const handleNewQuestionSpy = cy.spy().as('handleNewQuestionSpy');
		mount(<AnswerHeader ansCount={ansCount} title={title} handleNewQuestion={handleNewQuestionSpy} />);
		cy.contains('Ask a Question').click()
		cy.get('@handleNewQuestionSpy').should('be.called');
	});
})
