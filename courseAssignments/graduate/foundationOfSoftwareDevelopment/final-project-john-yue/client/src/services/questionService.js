import { REACT_APP_API_URL, api } from "./config";

const QUESTION_API_URL = `${REACT_APP_API_URL}/question`;

// To get Questions by Filter
const getQuestionsByFilter = async (order = "newest", search = "") => {
    try{
    const res = await api.get(
        `${QUESTION_API_URL}/getQuestion?order=${order}&search=${search}`
    );
    return res.data;
} catch (error) {
    console.error("Failed to getQuestionsByFilter:", error);}
};

// To get Questions by id
const getQuestionById = async (qid) => {
    try{
    const res = await api.get(`${QUESTION_API_URL}/getQuestionById/${qid}`);
    return res.data;
} catch (error) {
    console.error("Failed to getQuestionById:", error);}
};

// To add Questions
const addQuestion = async (q) => {
    try{
    const res = await api.post(`${QUESTION_API_URL}/addQuestion`, q);
    return res.data;
} catch (error) {
    console.error("Failed to addQuestion:", error);}
};

// To get Questions by Filter
const getQuestionsByUserFilter = async (user) => {
    try{
    const res = await api.get(
        `${QUESTION_API_URL}/getQuestionByUserFilter/${user}`
    );
	console.log(`getQuestionsByUserFilter res: ${JSON.stringify(res)}`)
    return res.data;
} catch (error) {
    console.error("Failed to getQuestionsByUserFilter:", error);}
};

// Toggle Qurstion status by id
const toggleQuestionById = async (qid,lockReason,username) => {

    try{
    const res = await api.post(`${QUESTION_API_URL}/toggleQuestionById/${qid}`, {lockReason, username});
    return res.data;
} catch (error) {
    console.error("Failed to toggleQuestionById:", error);}
};

const deleteQuestionById= async(qid) =>{
    try{
    const res = await api.post(`${QUESTION_API_URL}/deleteQuestionById/${qid}`);
    return res.data;
} catch (error) {
    console.error("Failed to deleteQuestionById:", error);}
}

export { getQuestionsByFilter, getQuestionById, addQuestion, getQuestionsByUserFilter,toggleQuestionById,deleteQuestionById };
