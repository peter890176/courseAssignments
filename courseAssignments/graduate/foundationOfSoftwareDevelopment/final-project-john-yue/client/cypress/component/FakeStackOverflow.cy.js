/*
	* Implementation for reference: 
	*
import React from "react";
import { useState } from "react";
import Header from "./header";
import Main from "./main";

export default function FakeStackOverflow() {
    const [search, setSearch] = useState("");
    const [mainTitle, setMainTitle] = useState("All Questions");

    const setQuestionPage = (search = "", title = "All Questions") => {
        setSearch(search);
        setMainTitle(title);
    };
    return (
        <>
            <Header search={search} setQuestionPage={setQuestionPage} />
            <Main
                title={mainTitle}
                search={search}
                setQuestionPage={setQuestionPage}
            />
        </>
    );
}
	* */


import { mount } from '@cypress/react';
import FakeStackOverflow from '../../src/components/fakestackoverflow'

describe('FakeStackOverflow Component', () => {
	it('renders all questions page by default', () => {
		mount(<FakeStackOverflow />)
		cy.contains('All Questions').should('be.visible')
	})
})
