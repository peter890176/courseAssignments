import React from 'react';
import { postVote } from '../../../services/voteService';
import { profile } from '../../../services/loginService';
import "./index.css";

function VoteButton({ questionId, updateVote, isLocked }) {
    const handleVote = async (direction) => {
		const user = await profile();

        if (!user) {
            alert('Please login to vote.');
            return;
        }

        const votes = await postVote(user.name, questionId, direction);


        updateVote(votes);
    };

    return (
        <>
            <button id="up-vote" onClick={() => handleVote('up')} disabled={isLocked}>Good ↑</button>
            <button id="down-vote" onClick={() => handleVote('down')} disabled={isLocked}>Bad ↓</button>
        </>
    );
}

export default VoteButton;
