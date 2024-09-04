import { useState } from "react";
import Form from "../baseComponents/form";
import Input from "../baseComponents/input";
import * as loginService from "../../../services/loginService"; 
import { useEffect } from "react";

const LoginPage = ({handleProfile}) => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [usernameErr, setUsernameErr] = useState("");
    const [passwordErr, setPasswordErr] = useState("");
    const [loginSuccess, setLoginSuccess] = useState("");

    useEffect(() => {
        if (loginSuccess) {
            handleProfile();
        }
    }, [loginSuccess, handleProfile]);

    const processLogin = async () => {
        let isValid = true;
        setUsernameErr("");
        setPasswordErr("");

        if (!username) {
            setUsernameErr("Username cannot be empty");
            isValid = false;
        } else {
            setUsernameErr("");
        }
        if (!password) {
            setPasswordErr("Password cannot be empty");
            isValid = false;
        } else {
            setPasswordErr("");
        }

        if (!isValid) {
            return;
        }
        const res = await loginService.login({ username, password });
        if (res.success=== true) {

            setLoginSuccess(true);
            handleProfile();
            
        } else {
            setPasswordErr("Invalid username or password");
        }
    };

    const processSignup = async () => {
        let isValid = true;
        setUsernameErr("");
        setPasswordErr("");

        if (!username) {
            setUsernameErr("Username cannot be empty");
            isValid = false;
        } else {
            setUsernameErr("");
        }
        if (!password) {
            setPasswordErr("Password cannot be empty");
            isValid = false;
        } else {
            setPasswordErr("");
        }

        if (!isValid) {
            return;
        }
        const res = await loginService.signup({ username, password });

        if (res.success=== true) {

			alert('Signup successful. Please login.');
            
        } else {
            setPasswordErr("Username already taken");
        }
    };

    

    return (
        <Form>
            <Input
                type={"text"}
                title={"Username"}
                id={"loginUsernameInput"}
                val={username}
                setState={setUsername}
                err={usernameErr}
            />
            <Input
                type={"password"}
                title={"Password"}
                id={"loginPasswordInput"}
                val={password}
                setState={setPassword}
                err={passwordErr}
            />
            <div className="btn_indicator_container">

                    <button
                        className="form_postBtn"
                        onClick={processLogin}
                    >
                        Login
                    </button>
					<button
						className="form_postBtn"
						onClick={processSignup}
					>
						Signup
					</button>

            </div>
        </Form>
    );
};

export default LoginPage;
