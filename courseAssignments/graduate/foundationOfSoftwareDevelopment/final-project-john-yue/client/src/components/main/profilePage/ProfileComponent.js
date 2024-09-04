import ProfileEditor from "./ProfileEditor"
import ProfileDetails from "./ProfileDetails"
import { useState, useEffect } from "react";
import "./index.css";

const ProfileComponent = ({
    title_text,
    order,
    setQuestionOrder,
    clickTag,
    handleAnswer,
    handleNewQuestion,
    handleProfile,
	user,
	setUser
}) => {
	const [edit, setEdit] = useState(false);

    return (
		<div>
		{user && (
			<>
			{!edit ? (
				<ProfileDetails 
					title_text={title_text}
					order={order}
					setQuestionOrder={setQuestionOrder} 
					clickTag={clickTag} 
					handleAnswer={handleAnswer}
					handleNewQuestion={handleNewQuestion} 
					handleProfile={handleProfile}
					user={user} 
					setUser={setUser}
					setEdit={setEdit}
				/>
			) : (
			<ProfileEditor user={user} setEdit={setEdit} setUser={setUser}/>
		)}
		</>
		)}
		</div>
    );
};

export default ProfileComponent;
