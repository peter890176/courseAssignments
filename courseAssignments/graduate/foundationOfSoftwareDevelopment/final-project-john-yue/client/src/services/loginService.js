import { REACT_APP_API_URL, api } from "./config";

const LOGIN_API_URL = `${REACT_APP_API_URL}/login`;

async function login(credentials) {
    try {
        const response = await api.post(`${LOGIN_API_URL}`, credentials);
        if(response.status===200){
			return { success: true, role: response.data.role, email:response.data.email };}
        else {
            return { success: false, message: 'Login failed. Please check your credentials and try again.' };
        }
    } catch (error) {
        console.error("Login error:", error);
        return { success: false, message: 'Login failed. Please check your credentials and try again.' };
    }
}

export const logout = async () => {
	await api.post(`${LOGIN_API_URL}/logout`);
};

export const profile = async () => {
	const response = await api.get(`${LOGIN_API_URL}/profile`);
	return response.data;
};

export const updateProfile = async (profile) => {
	await api.post(`${LOGIN_API_URL}/updateProfile`, profile);
}

export const getUsernameById = async (id) => {
	const url = `${LOGIN_API_URL}/username/${id}`;
	const response = await api.get(url);
	return response.data;
}

export const signup = async (credentials) => {
	const response = await api.post(`${LOGIN_API_URL}/signup`, credentials);

	if (response.status === 200) {
		return { success: true };
	}

	return { success: false };
}

export { login };
