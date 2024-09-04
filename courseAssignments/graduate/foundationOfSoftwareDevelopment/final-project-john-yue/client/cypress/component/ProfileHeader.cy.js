import { mount } from '@cypress/react';
import ProfileHeader from '../../src/components/main/profilePage/header'

describe('ProfileHeader Component', () => {
	it('display text correctly', () => {
		const mockTitleText = 'test-title'
		mount(<ProfileHeader title_text={mockTitleText} />);
		cy.contains(mockTitleText).should('be.visible')
	})
})
