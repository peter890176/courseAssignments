import { mount } from '@cypress/react';
import OrderButton from '../../src/components/main/questionPage/header/orderButton'
import { REACT_APP_API_URL } from '../../src/services/config'

const ANSWER_API_URL = `${REACT_APP_API_URL}/answer`
const LOGIN_API_URL = `${REACT_APP_API_URL}/login`

describe('OrderButton Component', () => {
	it('renders content and calls setQuestionOrder with correct arguments', () => {
		const mockMessage = 'test-message'
		const mockSetQuestionOrder = cy.stub()

		mount(<OrderButton setQuestionOrder={mockSetQuestionOrder} message={mockMessage} />);

		cy.contains(mockMessage).should('be.visible')
		cy.get('button').click()

		cy.wrap(mockSetQuestionOrder).should('be.called')
	})
})

