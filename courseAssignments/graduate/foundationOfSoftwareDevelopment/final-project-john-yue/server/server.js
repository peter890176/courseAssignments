// Application server
// Run this script to launch the server.
// The server should run on localhost port 8000.
// This is where you should start writing server-side code for this application.
const { MONGO_URL, port } = require("./config");
const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");
const session = require("express-session");
require('dotenv').config()

const SESSION_SECRET = process.env.SESSION_SECRET
//const MONGO_URL = "mongodb://127.0.0.1:27017/fake_so";
//const MONGO_URL = "mongodb://mongodb:27017/fake_so";


const CLIENT_URL = "http://localhost:3000";
//const port = 8000;

mongoose.connect(MONGO_URL);

const app = express();

/*
app.get("/", (_, res) => {
    res.send("Fake SO Server Dummy Endpoint");
    res.end();
});
*/

app.use(
    cors({
        credentials: true,
        origin: [CLIENT_URL],
    })
);

// Prevent stack traces or other sensitive information being displayed to the client in the event of an error
app.use((err, req, res, next) => {
	console.error(err.message)
	console.error(err.stack);
	res.status(500).send("Something broke on the server!");
});

const sessionOptions = {
	secret: SESSION_SECRET,
	resave: false,
	saveUninitialized: false,
	cookie: {
		maxAge: 1000 * 60 * 60,
		httpOnly: true,
	}
};

app.use(session(sessionOptions));

app.use(express.json());

app.get("", (req, res) => {
    res.send("hello world");
    res.end();
});

const questionController = require("./controller/question");
const tagController = require("./controller/tag");
const answerController = require("./controller/answer");
const voteController = require("./controller/vote");
const loginController = require("./controller/login");

app.use("/question", questionController);
app.use("/tag", tagController);
app.use("/answer", answerController);
app.use("/questions", voteController);
app.use("/login", loginController);

let server = app.listen(port, () => {
    console.log(`Server starts at http://localhost:${port}`);
});

process.on("SIGINT", () => {
    server.close();
    mongoose.disconnect();
    console.log("Server closed. Database instance disconnected");
    process.exit(0);
});

module.exports = server
