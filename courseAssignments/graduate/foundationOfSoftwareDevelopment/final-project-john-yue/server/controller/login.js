const express = require("express");
const User = require("../models/users");

const router = express.Router();

const login = async (req, res) => {
    const { username, password } = req.body; 
    try {
        const user = await User.findOne({ name: username });

        if (!user) {
            return res.status(404).json({ message: "User not found" });
        }

        if (password !== user.password) {
            return res.status(401).json({ message: "Invalid credentials" });
        }

		req.session['user'] = user;
		res.sendStatus(200);
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: "Server error" });
    }

};

const logout = async (req, res) => {
	try{
	req.session.destroy();
	res.sendStatus(200);
} catch (error) {
    console.error("Failed to logout:", error);}
}

const profile = async (req, res) => {
	try{
	const user = req.session['user'];
	if (!user) {
		res.json(null);
		return
	}

	res.json(user);
} catch (error) {
    console.error("Failed to load Profile:", error);}
}

const updateProfile = async (req, res) => {
	try{
	const user = req.session['user'];
	if (!user) {
		res.sendStatus(401)
		return
	}

	await User.findByIdAndUpdate(user._id, req.body)
	req.session['user'] = req.body;
	res.sendStatus(200);
} catch (error) {
    console.error("Failed to uodate profile:", error);}
}

const getUsernameById = async (req, res) => {
	try{
	const { id } = req.params;
	const user = await User.findById(id);
	res.json(user.name);
} catch (error) {
    console.error("Failed to get username:", error);}
}

const signup = async (req, res) => {
	try{
	const { username, password } = req.body;
	const newUser = new User({
		name: username,
		password: password,
		role: "normal",
		email: "setyour@email.com"
	});
	
	await newUser.save();
	res.sendStatus(200);
} catch (error) {
    console.error("Failed to sign up:", error);}
}

router.post("",login);
router.post("/logout", logout)
router.get("/profile", profile);
router.post("/updateProfile", updateProfile);
router.get("/username/:id", getUsernameById);
router.post("/signup", signup);

module.exports = router;
