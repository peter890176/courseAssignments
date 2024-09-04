import { handleHyperlink } from "../../../../tool";
import "./index.css";
import { useEffect, useState } from "react";
import { getUsernameById } from "../../../../services/loginService";
import he from "he";

// Component for the Answer Page
const Answer = ({ text, ansBy, meta, authorId=""}) => {
	if (text !== undefined) {
		text = he.encode(text, { useNamedReferences: true });
	}

    const [answerAuthor, setAnswerAuthor] = useState("");
    const fetchAuthor = async () => { 
        const res = await getUsernameById(authorId);
        setAnswerAuthor(res);
    };
    useEffect(() => {
        fetchAuthor();
    }, [authorId]);

    return (
        <div className="answer right_padding">
            <div id="answerText" className="answerText">
                {handleHyperlink(text)}
            </div>
            <div className="answerAuthor">
                <div className="answer_author">{answerAuthor}</div>
                <div className="answer_question_meta">{meta}</div>
            </div>
        </div>
    );
};

export default Answer;
