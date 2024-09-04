const express = require("express");
const Question = require("../models/questions");
const User = require("../models/users");
const router = express.Router();

const voteOnQuestion = async (req, res) => {
    try {
    const { qid } = req.params;
    const { vote, username } = req.body;
	const user = await User.findOne({ name: username });
    const increment = vote === 'up' ? 1 : -1;


        const question = await Question.findById(qid);
        const userHasVoted = question.votedUsers.find(u => u.id.toString() === user._id.toString());
        
        if (userHasVoted) {
            //voted user
            if (userHasVoted.voteType === vote) {
                return res.json({votes: question.votes, duplicate: true, cancel:false });
            } else {
                const newIncrement = vote === 'up' ? 1 : -1;
                const originalState = await Question.findByIdAndUpdate(
                    qid,
                    { 
                        $inc: { votes: newIncrement },
                        $pull: { votedUsers: { id: user._id } }
                    },{ new: true }
                );

                
                return res.json({ votes: originalState.votes,  duplicate: false, cancel:true});
            }
        } else {
            // New vote
            const updatedQuestion = await Question.findByIdAndUpdate(
                qid,
                { 
                    $inc: { votes: increment },
                    $push: { votedUsers: { id: user._id, voteType: vote } }
                },
                { new: true }
            );
            return res.json({ votes: updatedQuestion.votes });
        }
    } catch (error) {
        console.error("Error processing vote:", error);
        res.status(500).send(error.message);
    }
};

router.post('/:qid/vote', voteOnQuestion);

module.exports = router;
