describe("Cypress e2e tests for Feature 7: User Profiles", () => {
	beforeEach(() => {
		cy.exec("node ../server/controller/destroy.js");
		cy.exec("node ../server/controller/init.js");
	});

	it("Can log in and out", () => {
		cy.visit("http://localhost:3000");

		cy.get("#sideBarNav")
			.contains("User Profile")
			.click();

		cy.get("#loginUsernameInput").should('be.visible').type("saltyPeter");
		cy.get("#loginPasswordInput").should('be.visible').type("saltyPeter");

		cy.contains("Login").click()

		cy.contains("Profile for saltyPeter")
		cy.contains("saltypeter@gmail.com")
		cy.contains("saltyPeter's questions: 1")

		cy.get("#question_list").should('have.length', 1)
		cy.get("#question_list").contains("android studio save string shared preference, start activity and load the saved string")

		// Test persistence of login by navigating to Questions in the sidebar and then navigating back to User Profile
		cy.get("#sideBarNav")
			.contains("Questions")
			.click();

		cy.get("#sideBarNav")
			.contains("User Profile")
			.click();

		cy.contains("Profile for saltyPeter")

		// Log out, test log out persistence
		cy.contains("Logout").click()
		cy.contains("Login").should('be.visible')

		cy.get("#sideBarNav")
			.contains("Questions")
			.click();

		cy.get("#sideBarNav")
			.contains("User Profile")
			.click();

		cy.contains("Login").should('be.visible')
	});

	it("Invalid credentials prevents login", () => {
		cy.visit("http://localhost:3000");

		cy.contains("User Profile").click();

		cy.get("#loginUsernameInput").should('be.visible').type("badUsername");
		cy.get("#loginPasswordInput").should('be.visible').type("badPassword");

		cy.contains("Login").click();

		cy.contains("Invalid username or password")
		cy.contains("Login").should('be.visible')
		cy.contains("Profile for").should('not.exist')

		cy.get("#loginUsernameInput").should('be.visible').clear().type("saltyPeter");

		cy.contains("Login").click();

		cy.contains("Invalid username or password")
		cy.contains("Login").should('be.visible')
		cy.contains("Profile for").should('not.exist')

		cy.get("#loginPasswordInput").should('be.visible').clear().type("saltyPeter");
		cy.get("#loginUsernameInput").should('be.visible').clear().type("badUsername");

		cy.contains("Login").click();

		cy.contains("Invalid username or password")
		cy.contains("Login").should('be.visible')
		cy.contains("Profile for").should('not.exist')

		cy.get("#loginUsernameInput").should('be.visible').clear().type("saltyPeter");

		cy.contains("Login").click();

		cy.contains("Profile for saltyPeter").should('be.visible');
	})

	it("Can edit user profile", () => {
		cy.visit("http://localhost:3000");

		cy.contains("User Profile").click();
		cy.contains("Login").should('be.visible')

		cy.get("#loginUsernameInput").type("saltyPeter");
		cy.get("#loginPasswordInput").type("saltyPeter");

		cy.contains("Login").click()

		cy.contains("Profile for saltyPeter").should('be.visible');

		cy.contains("Edit Profile").click();

		cy.contains("Username").should('be.visible');
		cy.contains("Password").should('be.visible');
		cy.contains("Email").should('be.visible');

		cy.get("#username-edit").should('be.visible').clear().type("newUsername");
		cy.get("#password-edit").should('be.visible').clear().type("newPassword");
		cy.get("#email-edit").should('be.visible').clear().type("newEmail@email.email");

		// Test not saving
		cy.contains('Back to Details').click();
		cy.contains("Profile for saltyPeter").should('be.visible');

		cy.contains("Edit Profile").click();

		cy.get("#username-edit").should('have.value', 'saltyPeter');
		cy.get("#password-edit").should('have.value', 'saltyPeter');
		cy.get("#email-edit").should('have.value', 'saltypeter@gmail.com');

		cy.get("#username-edit").should('be.visible').clear().type("newUsername");
		cy.get("#password-edit").should('be.visible').clear().type("newPassword");
		cy.get("#email-edit").should('be.visible').clear().type("newEmail@email.email");

		// Test saving
		cy.contains('Save').click();
		cy.contains('Back to Details').click();

		cy.contains("Profile for newUsername").should('be.visible');
		cy.contains("Email: newEmail@email.email").should('be.visible');
		cy.contains("newUsername's questions: 1").should('be.visible');

		cy.contains("Logout").click()

		// Check old credentials don't work for login
		cy.get("#loginUsernameInput").should('be.visible').type("saltyPeter");
		cy.get("#loginPasswordInput").should('be.visible').type("saltyPeter");

		cy.contains("Login").click();

		cy.contains("Invalid username or password")
		cy.contains("Login").should('be.visible')
		cy.contains("Profile for").should('not.exist')

		// Check new credentials work for login
		cy.get("#loginUsernameInput").should('be.visible').clear().type("newUsername");
		cy.get("#loginPasswordInput").should('be.visible').clear().type("newPassword");

		cy.contains("Login").click()

		cy.contains("Profile for newUsername").should('be.visible');
		cy.contains("Username: newUsername").should('be.visible');
		cy.contains("Email: newEmail@email.email").should('be.visible');
		cy.contains("newUsername's questions: 1").should('be.visible');
	});

	it("Gracefully handles blank fields", () => {
		cy.visit("http://localhost:3000");

		cy.contains("User Profile").click();
		cy.contains("Login").should('be.visible')

		cy.get("#loginUsernameInput").type("saltyPeter");
		cy.get("#loginPasswordInput").type("saltyPeter");

		cy.contains("Login").click()

		cy.contains("Profile for saltyPeter").should('be.visible');

		cy.contains("Edit Profile").click();

		cy.contains("Username").should('be.visible');
		cy.contains("Password").should('be.visible');
		cy.contains("Email").should('be.visible');

		cy.get("#username-edit").should('be.visible').clear();
		cy.contains('Username cannot be empty').should('be.visible');
		cy.get("#username-edit").should('be.visible').type("newUsername");
		cy.contains('Username cannot be empty').should('not.exist');

		cy.get("#password-edit").should('be.visible').clear();
		cy.contains('Password cannot be empty').should('be.visible');
		cy.get("#password-edit").should('be.visible').type("newPassword");
		cy.contains('Password cannot be empty').should('not.exist');

		cy.get("#email-edit").should('be.visible').clear();
		cy.contains('Email cannot be empty').should('be.visible');
		cy.get("#email-edit").should('be.visible').type("newEmail@email.email");
		cy.contains('Email cannot be empty').should('not.exist');

		cy.get("#password-edit").should('be.visible').clear();

		cy.contains('Save').click();
		cy.contains('Back to Details').click();

		cy.contains("Profile for newUsername").should('not.exist');
		cy.contains("Profile for saltyPeter").should('be.visible');
	});

	it("Question metadata updates when user changes username", () => {
		cy.visit("http://localhost:3000");

		cy.contains("User Profile").click();
		cy.contains("Login").should('be.visible')

		cy.get("#loginUsernameInput").type("saltyPeter");
		cy.get("#loginPasswordInput").type("saltyPeter");

		cy.contains("Login").click()

		cy.contains("Profile for saltyPeter").should('be.visible');

		cy.contains("Edit Profile").click();

		cy.get("#username-edit").should('be.visible').clear().type("newUsername");

		cy.contains('Save').click();
		cy.contains('Back to Details').click();

		cy.contains("Profile for newUsername").should('be.visible');
		cy.contains("newUsername's questions: 1").should('be.visible');

		cy.get(".question_author").contains("newUsername").should('be.visible');

		cy.contains("Questions").click();

		cy.contains("newUsername").should('be.visible');
	});

	it('Can sign up with a new profile', () => {
		cy.visit("http://localhost:3000");

		cy.contains("User Profile").click();
		cy.contains("Login").should('be.visible')

		cy.get("#loginUsernameInput").type("newUser");
		cy.get("#loginPasswordInput").type("newUserPassword");

		cy.contains("Login").click()
		cy.contains("Invalid username or password").should('be.visible')

		cy.contains("Signup").click();
		cy.contains("Login").click()
		cy.contains("Profile for newUser").should('be.visible');

		cy.contains("Logout").click()

		cy.contains("Login").should('be.visible')

		cy.get("#loginUsernameInput").type("newUser");
		cy.get("#loginPasswordInput").type("newUserPassword");

		cy.contains("Login").click()
		cy.contains("Profile for newUser").should('be.visible');
	});
});
