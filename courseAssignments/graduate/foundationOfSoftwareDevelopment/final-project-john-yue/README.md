# Final Team Project for CS5500

Login with your Northeastern credentials and read the project description [here](https://northeastern-my.sharepoint.com/:w:/g/personal/j_mitra_northeastern_edu/ETUqq9jqZolOr0U4v-gexHkBbCTAoYgTx7cUc34ds2wrTA?e=URQpeI).

## List of features

All the features you have implemented. 

Writing out the root filepath to the component tests so that I don't have to fill up the table with lengthy repetitive filepaths: client/cypress/component/

The assignment had me write one component for each React component, not one component for each feature. Of course many of the components are involved in many features so I'm just writing the most relevant ones in each line of the table, but this is by no means an exhaustive list of component tests. Please find this in the component test root directory mentioned above. 

| Feature   | Description     | E2E Tests      | Component Tests | Jest Tests     |
|-----------|-----------------|----------------|-----------------|----------------|
| Feature 1: View Posts | Users can navigate to the main screen with the question list and then click on a question to see the question text and associated answers. View increment is reflected on the screen. | testing/cypress/e2e/viewPosts.cy.js | AnswerPage.cy.js, QuestionPage.cy.js, Question.cy.js, QuestionBody.cy.js | server/tests/viewPosts.test.js |
| Feature 2: Create Posts | Users can create a new post and add tags to it if they are logged in - non-authenticated users cannot create posts. | testing/cypress/e2e/createPosts.cy.js | NewQuestion.cy.js | server/tests/createPosts.test.js |
| Feature 3: Search Posts | Users can search for existing posts by keywords and/or tags. Users can order search results based on newest post, most recent activity, or filter posts to show only those without answers. | testing/cypress/e2e/searchPosts.cy.js | Header.cy.js, QuestionPage.cy.js | server/tests/searchPosts.test.js |
| Feature 4: Answer/Comment On Posts | Users can answer questions in posts by leaving comments on the post if they are logged in - non-authenticated users cannot create comments. | testing/cypress/e2e/answerPosts.cy.js | Answer.cy.js, AnswerHeader.cy.js, AnswerPage.cy.js, NewAnswer.cy.js | server/tests/answerPosts.test.js |
| Feature 5: Vote On Posts | Users can 'up-vote' and 'down-vote' posts, exactly once per post. Non-authenticated users cannot vote. | testing/cypress/e2e/votingOnPosts.cy.js| VoteButton.cy.js | server/tests/votingOnPosts.test.js |
| Feature 6: Tag Posts | Tags are created during post creation. Users can search for posts via tags and view a list of all tags and then list all questions associated with a particular tag. | testing/cypress/e2e/taggingPosts.cy.js | Tag.cy.js, TagPage.cy.js, NewQuestion.cy.js, Question.cy.js | server/tests/taggingPosts.test.js |
| Feature 7: User Profiles | Users can create new profiles, view and edit their profile information, and log out. | testing/cypress/e2e/userProfiles.cy.js | ProfilePage.cy.js, ProfileComponent.cy.js, ProfileDetails.cy.js, ProfileEditor.cy.js, ProfileHeader.cy.js, ProfileQuestion.cy.js | server/tests/userProfiles.test.js |
| Feature 8: Moderate Posts | Users with role == 'admin' can delete posts so they are not visible to other users, and can lock posts for particular reasons so that other users cannot vote or comment on the post. They can also unlock previously locked posts. | testing/cypress/e2e/moderatePosts.cy.js | AnswerPage.cy.js | moderatePosts.test.js |

## Instructions to generate and view coverage report 
The Jest coverage report is generated automatically upon a pull request being opened to merge a branch into main. The coverage directory is uploaded as a GitHub artifact in the action run. Go to the most recent run of the 'Server-Side Jest Tests' workflow and click into it and scroll down to the artifacts section. Download the 'coverage' folder, extract the .zip, and open up lcov-report/index.html to see the full Jest coverage report.

The actual Jest and Cypress test results can be seen in the console output provided by the GitHub Actions GUI interface on the website.

## Extra Credit Section (if applicable)
