import "./index.css";
import React from "react";
import he from "he";
import { handleHyperlink } from "../../../../tool";
import { useEffect, useState } from "react";
import { getUsernameById } from "../../../../services/loginService";

// Component for the Question's Body
const QuestionBody = ({ views, text, votes, meta, authorId=""}) => {
    const [questionAuthor, setQuestionAuthor] = useState("");

    const fetchAuthor = async () => { 
        if (authorId) {
            const res = await getUsernameById(authorId);
            setQuestionAuthor(res || "Unknown Author");
        } else {
            setQuestionAuthor("Unknown Author");
        }
    };
    useEffect(() => {
		fetchAuthor();
	}, []);

	if (text !== undefined) {
		text = he.encode(text, { useNamedReferences: true });
	}

    return (
        <div id="questionBody" className="questionBody right_padding">
            <div className="bold_title answer_question_view">{views} views</div>
            <div className="bold_title answer_question_view">{votes} votes</div>
            <div className="answer_question_text">{handleHyperlink(text)}</div>
            <div className="answer_question_right">
            <div className="question_author">{questionAuthor}</div>
                <div className="answer_question_meta">asked {meta}</div>
            </div>
        </div>
    );
};

export default QuestionBody;
