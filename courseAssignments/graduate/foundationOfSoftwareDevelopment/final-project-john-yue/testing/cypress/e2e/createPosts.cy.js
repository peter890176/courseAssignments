describe("Cypress e2e tests for Feature 2: Creating Posts", () => {
	beforeEach(() => {
		cy.exec("node ../server/controller/destroy.js");
		cy.exec("node ../server/controller/init.js");
	});

	it("Can't create a post if not logged in", () => {
		cy.visit("http://localhost:3000");

		const stub = cy.stub()
		cy.on('window:alert', stub)

		cy.get("#right_main")
			.contains("Ask a Question")
			.click()
			.then(() => {
				expect(stub.getCall(0)).to.be.calledWith('Please login first.')
			});

	});

	it("Can't create a post if not logged in - answer screen", () => {
		cy.visit("http://localhost:3000");

		const stub = cy.stub()
		cy.on('window:alert', stub)

		cy.get(".postTitle").first().click();

		cy.get("#right_main")
			.contains("Ask a Question")
			.click()
			.then(() => {
				expect(stub.getCall(0)).to.be.calledWith('Please login first.')
			});
	});

	it("Can't create a post if not logged in - tags screen", () => {
		cy.visit("http://localhost:3000");
		cy.contains("All Questions")

		const stub = cy.stub()
		cy.on('window:alert', stub)

		cy.get("#sideBarNav")
			.contains("Tags")

		cy.get("#right_main")
			.contains("Ask a Question")
			.should('be.visible')
			.click()
			.then(() => {
				expect(stub.getCall(0)).to.be.calledWith('Please login first.')
			});
	});

	it("Can login and create a post", () => {
		cy.visit("http://localhost:3000");

		cy.get("#sideBarNav")
			.contains("User Profile")
			.click();

		cy.get("#loginUsernameInput").should('be.visible').type("saltyPeter");
		cy.get("#loginPasswordInput").should('be.visible').type("saltyPeter");

		cy.contains("Login").click();

		cy.get("#sideBarNav")
			.contains("Questions")
			.click();

		cy.contains("Ask a Question").click();

		cy.contains("Question Title").should('be.visible');
		cy.contains("Question Text").should('be.visible');
		cy.contains("Tags").should('be.visible');
		cy.contains("Post Question").should('be.visible');

		cy.get("#formTitleInput").type("How to create a test in Cypress?");
		cy.get("#formTextInput").type("I want to learn how to create a test in Cypress.");
		cy.get("#formTagInput").type("e2e");

		cy.contains("Post Question").click();

		cy.contains("All Questions").should('be.visible');
		cy.contains("How to create a test in Cypress?").should('be.visible');
		cy.contains("e2e").should('be.visible');

		cy.contains("User Profile").click();
		cy.contains("How to create a test in Cypress?").should('be.visible');
	});

	it("Fields can't be blank", () => {
		cy.visit("http://localhost:3000");

		cy.get("#sideBarNav")
			.contains("User Profile")
			.click();

		cy.get("#loginUsernameInput").should('be.visible').type("saltyPeter");
		cy.get("#loginPasswordInput").should('be.visible').type("saltyPeter");

		cy.contains("Login").click();

		cy.get("#sideBarNav")
			.contains("Questions")
			.click();

		cy.contains("Ask a Question").click();

		cy.contains("Question Title").should('be.visible');
		cy.contains("Question Text").should('be.visible');
		cy.contains("Tags").should('be.visible');
		cy.contains("Post Question").should('be.visible');

		cy.get("#formTextInput").type("I want to learn how to create a test in Cypress.");
		cy.get("#formTagInput").type("e2e");

		cy.contains("Post Question").click();

		cy.contains("Title cannot be empty").should('be.visible');
		cy.contains("All Questions").should('not.exist');

		cy.get("#formTitleInput").type("How to create a test in Cypress?");
		cy.get("#formTextInput").clear();

		cy.contains("Post Question").click();

		cy.contains("Question text cannot be empty").should('be.visible');
		cy.contains("Title cannot be empty").should('not.exist');
		cy.contains("All Questions").should('not.exist');

		cy.get("#formTextInput").type("I want to learn how to create a test in Cypress.");
		cy.get("#formTagInput").clear();

		cy.contains("Post Question").click();

		cy.contains("Should have at least 1 tag").should('be.visible');
		cy.contains("Question text cannot be empty").should('not.exist');
		cy.contains("All Questions").should('not.exist');

		cy.get("#formTitleInput").clear();
		cy.get("#formTextInput").clear();

		cy.contains("Post Question").click();

		cy.contains("Title cannot be empty").should('be.visible');
		cy.contains("Question text cannot be empty").should('be.visible');
		cy.contains("Should have at least 1 tag").should('be.visible');

		cy.contains("All Questions").should('not.exist');

		cy.get("#formTitleInput").type("How to create a test in Cypress?");
		cy.get("#formTextInput").type("I want to learn how to create a test in Cypress.");
		cy.get("#formTagInput").type("e2e");

		cy.contains("Post Question").click();

		cy.contains("All Questions").should('be.visible');
		cy.contains("How to create a test in Cypress?").should('be.visible');
	});

	it("Fields can't exceed their limits", () => {
		cy.visit("http://localhost:3000");

		cy.get("#sideBarNav")
			.contains("User Profile")
			.click();

		cy.get("#loginUsernameInput").should('be.visible').type("saltyPeter");
		cy.get("#loginPasswordInput").should('be.visible').type("saltyPeter");

		cy.contains("Login").click();

		cy.get("#sideBarNav")
			.contains("Questions")
			.click();

		cy.contains("Ask a Question").click();

		cy.contains("Question Title").should('be.visible');
		cy.contains("Question Text").should('be.visible');
		cy.contains("Tags").should('be.visible');
		cy.contains("Post Question").should('be.visible');

		cy.get("#formTitleInput").type("extraordinarily long title".repeat(8));
		cy.get("#formTextInput").type("I want to learn how to create a test in Cypress.");
		cy.get("#formTagInput").type("e2e ".repeat(6));

		cy.contains("Post Question").click();

		cy.contains("Title cannot be more than 100 characters").should('be.visible');
		cy.contains("Cannot have more than 5 tags").should('be.visible');
		cy.contains("All Questions").should('not.exist');

	});
});
