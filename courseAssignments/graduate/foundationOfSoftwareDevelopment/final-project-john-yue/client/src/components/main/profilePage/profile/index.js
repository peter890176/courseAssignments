import { useEffect, useState } from "react";
import { getMetaData } from "../../../../tool";
import { getUsernameById } from "../../../../services/loginService";
import "./index.css";

const ProfileQuestion = ({ q, clickTag, handleAnswer }) => {
	const [questionAuthor, setQuestionAuthor] = useState("");

	const fetchAuthor = async () => {
		const author = await getUsernameById(q.asked_by_id);
		console.log(`author: ${author}`); 
		setQuestionAuthor(author);
	}

	useEffect(() => {
		fetchAuthor();
	}, []);

    return (
        <div
            className="question right_padding"
            onClick={() => {
                handleAnswer(q._id);
            }}
        >
            <div className="postStats">
                <div>{q.answers.length || 0} answers</div>
                <div>{q.views} views</div>
                <div>{q.votes} votes</div>
            </div>
            <div className="question_mid">
                <div className="postTitle">{q.title}</div>
                <div className="question_tags">
                    {q.tags.map((tag, idx) => {
                        return (
                            <button
                                key={idx}
                                className="question_tag_button"
                                onClick={(e) => {
                                    e.stopPropagation();
                                    clickTag(tag.name);
                                }}
                            >
                                {tag.name}
                            </button>
                        );
                    })}
                </div>
            </div>
            <div className="lastActivity">
                <div className="question_author">{questionAuthor}</div>
                <div>&nbsp;</div>
                <div className="question_meta">
                    asked {getMetaData(new Date(q.ask_date_time))}
                </div>
            </div>
        </div>
    );
};

export default ProfileQuestion;
