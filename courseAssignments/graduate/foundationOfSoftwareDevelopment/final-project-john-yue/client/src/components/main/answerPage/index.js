import { useEffect, useState } from "react";
import { getMetaData } from "../../../tool";
import Answer from "./answer";
import AnswerHeader from "./header";
import "./index.css";
import QuestionBody from "./questionBody";
import { getQuestionById } from "../../../services/questionService";
import { toggleQuestionById } from "../../../services/questionService";
import { deleteQuestionById } from "../../../services/questionService";
import { profile } from "../../../services/loginService";
import VoteButton from'./VoteButton';
import { getUsernameById } from "../../../services/loginService";

// Component for the Answers page
const AnswerPage = ({ qid, handleNewQuestion, handleNewAnswer }) => {
    const [isAdmin, setIsAdmin] = useState(false);
    const [username, setUsername] = useState('');
    const [question, setQuestion] = useState({});
    const [isLocked, setIsLocked] = useState();
    const [deleteSuccess, setDeleteSuccess] = useState(false);
    const [showDeleteConfirm, setShowDeleteConfirm] = useState(false);
    const [lockReason, setLockReason] = useState('');
    const [lockedBy, setLockedBy] = useState('');
    const [showLockReason, setShowLockReason] = useState();

	const fetchProfile = async () => {
		const u = await profile();
		if (!u) {
			return;
		}
		setUsername(u.name);
		setIsAdmin(u.role === 'admin');
	}

    useEffect(() => {
        const fetchData = async () => {
            let res = await getQuestionById(qid);
            if (res) {
                setQuestion(res);
                setIsLocked(res.lock === 'locked');
                setLockReason(res.lockReason || '');
                setShowLockReason(res.lock === 'locked');
                if (res.lock === 'locked' && res.lockByWhichAdminId) {
                    const adminUsername = await getUsernameById(res.lockByWhichAdminId);
                    setLockedBy(adminUsername);
                }
            }
        };

		fetchProfile();
        fetchData().catch((e) => console.log(e));
    }, [qid]);

    useEffect(() => {
        if (isLocked !== undefined) {
            setShowLockReason(isLocked);
            if (!isLocked) {
                setLockReason('');
                setLockedBy('');
            }
        }
    }, [isLocked]);


    const updateVote = (newVotes) => {
        setQuestion(prevQuestion => ({
            ...prevQuestion,
            votes: newVotes.votes
        }));
    };

    const handleDeleteConfirmation = () => {
        setShowDeleteConfirm(true);
    };

    const deleteQuestion = async () => {
        const res = await deleteQuestionById(qid);
        if (res) {
            setDeleteSuccess(true);
            setShowDeleteConfirm(false);
        }
    };

    if (deleteSuccess) {
        return <div className="successMessage">Question deleted successfully</div>;
    }
    

    //    const isLocked = (question.lock === 'locked');
    const toggleLock = async () => {
        if (!isLocked && !lockReason) {
            alert('Please enter a reason to lock the question.');
            return;
        }
        await toggleQuestionById(qid,lockReason,username );

        const res = await getQuestionById(qid);
        if (res) {
            setQuestion(res);
            setIsLocked(res.lock === 'locked');
            setLockReason(res.lockReason);
            setShowLockReason(res.lock === 'locked');
            if (res.lock === 'locked' && res.lockByWhichAdminId) {
                const adminUsername = await getUsernameById(res.lockByWhichAdminId);
                setLockedBy(adminUsername);
            }


        }        
        };

    return (
        <>
            <AnswerHeader
                ansCount={
                    question && question.answers && question.answers.length
                }
                title={question && question.title}
                handleNewQuestion={handleNewQuestion}
            />
            <VoteButton questionId={qid} updateVote={updateVote} isLocked={isLocked} />
            <QuestionBody
                views={question && question.views}
                text={question && question.text}
                votes = {question && question.votes}
                meta={question && getMetaData(new Date(question.ask_date_time))}
                authorId = {question && question.asked_by_id}
            />

            {question &&
                question.answers &&
                question.answers.map((a, idx) => (
                    <Answer
                        key={idx}
                        text={a.text}
                        ansBy={a.ans_by}
                        authorId={a.ans_by_id}
                        meta={getMetaData(new Date(a.ans_date_time))}
                        q = {question}
                    />
                ))}
            <button
                className="bluebtn ansButton"
                disabled={isLocked}
                onClick={() => {
                    handleNewAnswer();
                }}
            >
                {isLocked ? "Question is locked" : "Answer Question"}
            </button>
            {isAdmin && (
                <>
                    <button
                        className="bluebtn lockButton"
                        onClick={toggleLock}
                    >
                    {isLocked ? "Unlock Question" : "Lock Question"}
                    </button>
                    {!isLocked && (
            <input 
                type="text" 
                placeholder="Enter lock reason" 
                value={lockReason} 
                onChange={e => setLockReason(e.target.value)} 
            />
        )}
                    <button className="bluebtn deleteButton" onClick={handleDeleteConfirmation}>
                        Delete Question
                    </button>
                </>
            )}
{showLockReason&& (
    <div style={{ color: 'red' }}>
        Locked by {lockedBy}. Locked Reason:"{question.lockReason}"
    </div>
)}

            {showDeleteConfirm && (
                <div className="confirm-dialog">
                    <p>Are you sure you want to delete this question? This cannot be undone.</p>
                    <button onClick={deleteQuestion}>Confirm</button>
                    <button onClick={() => setShowDeleteConfirm(false)}>Cancel</button>
                </div>
            )}

        </>
    );
};

export default AnswerPage;
