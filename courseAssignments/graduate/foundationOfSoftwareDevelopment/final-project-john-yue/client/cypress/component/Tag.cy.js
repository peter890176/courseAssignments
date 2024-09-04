/*Implementation for reference: 
	*
import "./index.css";

const Tag = ({ t, clickTag }) => {
    return (
        <div
            className="tagNode"
            onClick={() => {
                clickTag(t.name);
            }}
        >
            <div className="tagName">{t.name}</div>
            <div>{t.qcnt} questions</div>
        </div>
    );
};

export default Tag;
	* */

import { mount } from '@cypress/react';
import Tag from '../../src/components/main/tagPage/tag'

describe('Tag Component', () => {
	it('display text correctly', () => {
		const mockTag = {
			name: 'test-tag',
			qcnt: 1
		}
		const clickTag = () => {}

		mount(<Tag t={mockTag} clickTag={clickTag} />);

		cy.contains(mockTag.name).should('be.visible')
		cy.contains(`${mockTag.qcnt} questions`).should('be.visible')
	})

	it('click event', () => {
		const mockTag = {
			name: 'test-tag',
			qcnt: 1
		}
		const clickTag = cy.stub().as('clickTag')

		mount(<Tag t={mockTag} clickTag={clickTag} />);
		
		cy.get('.tagNode').click()
		cy.get('@clickTag').should('have.been.calledWith', mockTag.name)
	})
})
