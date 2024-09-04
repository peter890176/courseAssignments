import { useState, useEffect } from "react";
import { updateProfile } from "../../../services/loginService";
import "./index.css";

const ProfileEditor = ({ user, setEdit, setUser }) => {
	const [tempUser, setTempUser] = useState(user);

	const backToDetails = () => {
		setEdit(false);
	}

	const saveEdits = async () => {
		if (tempUser.name === "" || tempUser.password === "" || tempUser.email === "") {
			alert("Please fill out all fields");
			return;
		}

		console.log(`made it to saveEdits`)
		await updateProfile(tempUser);
		console.log(`setting user to tempuser in saveEdits: ${JSON.stringify(tempUser)}`)
		alert("Successfully saved!");
		setUser(tempUser);
	}

	return (
		<div>
			<div>
				<label htmlFor="username-edit">Username</label>
				<input id="username-edit" type="text" value={tempUser.name} onChange={(e) => setTempUser({ ...tempUser, name: e.target.value })} />
				{tempUser.name === "" && <p>Username cannot be empty</p>}
			</div>

			<div>
				<label htmlFor="password-edit">Password</label>
				<input id="password-edit" type="password" value={tempUser.password} onChange={(e) => setTempUser({ ...tempUser, password: e.target.value })} />
				{tempUser.password === "" && <p>Password cannot be empty</p>}
			</div>

			<div>
				<label htmlFor="email-edit">Email</label>
				<input id="email-edit" type="email" value={tempUser.email} onChange={(e) => setTempUser({ ...tempUser, email: e.target.value })} />
				{tempUser.email === "" && <p>Email cannot be empty</p>}
			</div>

			<div>
				<button onClick={backToDetails}>Back to Details</button>
				<button onClick={saveEdits}>Save</button>
			</div>

		</div>

	)
};

export default ProfileEditor;
