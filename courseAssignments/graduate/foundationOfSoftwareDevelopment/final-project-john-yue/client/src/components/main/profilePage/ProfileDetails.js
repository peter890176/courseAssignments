import { useState, useEffect } from "react";
import { getQuestionsByUserFilter } from "../../../services/questionService";
import { logout } from "../../../services/loginService";
import ProfileHeader from "./header"
import ProfileQuestion from "./profile"
import "./index.css";

const ProfileDetails = (
	{
		title_text,
		order,
		setQuestionOrder,
		clickTag,
		handleAnswer,
		handleNewQuestion,
		handleProfile,
		user,
		setUser,
		setEdit
	}
) => {
	const [qlist, setQlist] = useState([]);

	const fetchQList = async () => {
		const ql = await getQuestionsByUserFilter(user._id);
		console.log(`in fetchQList: ${JSON.stringify(ql)}`)
		setQlist(ql);
	}

	const UIlogout = async () => {
		await logout();
		setUser(null)
	}

	const goToProfileEditor = () => {
		setEdit(true);
	}

	useEffect(() => {
		fetchQList();
	}, []);


	return (
		<div>
		<ProfileHeader
			title_text={`Profile for ${user.name}`} 
			setQuestionOrder={setQuestionOrder}
			handleNewQuestion={handleNewQuestion}
/>
		<div className="profile-details">
			<h3>Summary</h3>
			<p>Username: {user.name}</p>
			<p>Email: {user.email}</p>
			<p>Role: {user.role} user</p>
			<h4>{`${user.name}'s questions: ${qlist.length}`}</h4> 
		</div>

		<div>
			<button onClick={goToProfileEditor}>Edit Profile</button>
			<button onClick={UIlogout} className="logoutButton">Logout</button>
		</div>
		<div id="question_list" className="question_list">
			{qlist.map((q, idx) => (
				<ProfileQuestion
					q={q}
					key={idx}
					clickTag={clickTag}
					handleAnswer={handleAnswer}
				/>
			))}
		</div>
		{title_text === "Search Results" && !qlist.length && (
			<div className="bold_title right_padding">
				No Questions Found
			</div>
		)}
		</div>
	)
}


export default ProfileDetails;
