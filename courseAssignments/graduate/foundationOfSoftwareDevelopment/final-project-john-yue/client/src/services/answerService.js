import { REACT_APP_API_URL, api } from "./config";

const ANSWER_API_URL = `${REACT_APP_API_URL}/answer`;

// To add answer
const addAnswer = async (qid, ans) => {
    const data = { qid: qid, ans: ans };
	console.log(`data: ${JSON.stringify(data)}`)
    try {
    const res = await api.post(`${ANSWER_API_URL}/addAnswer`, data);
    return res.data;
} catch (error) {
    console.error("Failed to add answer:", error);}
};

export { addAnswer };
