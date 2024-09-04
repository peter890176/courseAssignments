// src/services/voteService.js

import { REACT_APP_API_URL, api } from "./config";

async function postVote(username, questionId, direction) {
    try{
    const response = await api.post(`${REACT_APP_API_URL}/questions/${questionId}/vote`, {
        username:username,
        vote: direction
    })
    if (response.data.duplicate){alert('You have already voted.')}
    else if (response.data.cancel){alert('vote cancelled')}
    else{console.log('alerting vote successful'); alert('Vote successful!');
}
     return response.data;
    } catch (error) {
        console.error("Failed to postVote:", error);}

}
export { postVote };
