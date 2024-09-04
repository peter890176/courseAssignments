import "./index.css";
import { useState, useEffect } from "react";
import { profile } from "../../services/loginService";
import SideBarNav from "./sideBarNav";
import QuestionPage from "./questionPage";
import TagPage from "./tagPage";
import AnswerPage from "./answerPage";
import NewQuestion from "./newQuestion";
import NewAnswer from "./newAnswer";
import ProfilePage from "./profilePage";

const Main = ({ search = "", title, setQuestionPage }) => {
    const [page, setPage] = useState("home");
    const [questionOrder, setQuestionOrder] = useState("newest");
    const [qid, setQid] = useState("");
    let selected = "";
    let content = null;
	console.log(`search: ${search}`)

    const handleQuestions = () => {
        setQuestionPage();
        setPage("home");
    };

    const handleTags = () => {
        setPage("tag");
    };

    const handleAnswer = (qid) => {
        setQid(qid);
        setPage("answer");
    };

    const clickTag = (tname) => {
        setQuestionPage("[" + tname + "]", tname);
        setPage("home");
    };

    const handleNewQuestion = async () => {
		const user = await profile();
        if (!user) {
            alert('Please login first.');
        } else {
            setPage("newQuestion");
        }
    };

    const handleNewAnswer = async () => {
		const user = await profile();
        if (!user) {
            alert('Please login first.');
        } else {
            setPage("newAnswer");
        }
    };

    const handleProfile = () => {
        if (page === "profile") {
            setPage("");
            setTimeout(() => setPage("profile"), 0);
        } else {
            setPage("profile");
        }
    };
    

    const getQuestionPage = (order = "newest", search = "") => {
        return (
            <QuestionPage
                title_text={title}
                order={order}
                search={search}
                setQuestionOrder={setQuestionOrder}
                clickTag={clickTag}
                handleAnswer={handleAnswer}
                handleNewQuestion={handleNewQuestion}
            />
        );
    };

	console.log(`page: ${page}`)

	useEffect(() => {
		if (search) {
			setPage("home");
		}
	}, [search]);

    switch (page) {
        case "home": {
            selected = "q";
            content = getQuestionPage(questionOrder.toLowerCase(), search);
            break;
        }
        case "tag": {
            selected = "t";
            content = (
                <TagPage
                    clickTag={clickTag}
                    handleNewQuestion={handleNewQuestion}
                />
            );
            break;
        }
        case "answer": {
            selected = "";
            content = (
                <AnswerPage
                    qid={qid}
                    handleNewQuestion={handleNewQuestion}
                    handleNewAnswer={handleNewAnswer}
                />
            );
            break;
        }
        case "newQuestion": {
            selected = "";
            content = <NewQuestion handleQuestions={handleQuestions} />;
            break;
        }
        case "newAnswer": {
            selected = "";
            content = <NewAnswer qid={qid} handleAnswer={handleAnswer} />;
            break;
        }
        case "profile": {

            selected = "p";
            content = <ProfilePage 
            title_text={title}
            search={search}
            setQuestionOrder={setQuestionOrder}
            clickTag={clickTag}
            handleAnswer={handleAnswer}
            handleNewQuestion={handleNewQuestion}
            handleProfile={handleProfile}/>;
            break;
        }
        
        default:
            selected = "q";
            content = getQuestionPage();
            break;
    }

    return (
        <div id="main" className="main">
            <SideBarNav
                selected={selected}
                handleQuestions={handleQuestions}
                handleTags={handleTags}
                handleProfile={handleProfile}
            />
            <div id="right_main" className="right_main">
                {content}
            </div>
        </div>
    );
};

export default Main;
