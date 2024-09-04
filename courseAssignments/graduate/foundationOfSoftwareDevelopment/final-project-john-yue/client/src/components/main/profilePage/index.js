import ProfileComponent from "./ProfileComponent"
import LoginPage from "../loginPage"
import { profile } from "../../../services/loginService";
import { useEffect, useState } from "react";

const ProfilePage = ({
    title_text,
    order,
    setQuestionOrder,
    clickTag,
    handleAnswer,
    handleNewQuestion,
    handleProfile,
}) => {
	const [user, setUser] = useState(null);

	const fetchProfile = async () => {
		console.log(`fetchProfile called`)
		const u = await profile();
		console.log(`setting user in fetchProfile: ${JSON.stringify(u)}`)
		setUser(u);
	}

	useEffect(() => {
		fetchProfile();
	}, []);

    return (
        <>
			{!user ? 
				<LoginPage handleProfile={handleProfile} />
				 : 
			<ProfileComponent 
				title_text={title_text}
				order={order}
				setQuestionOrder={setQuestionOrder}
				clickTag={clickTag}
				handleAnswer={handleAnswer}
				handleNewQuestion={handleNewQuestion}
				handleProfile={handleProfile}
				user={user}
				setUser={setUser}
			/>
			}
        </>
    );
};

export default ProfilePage;
