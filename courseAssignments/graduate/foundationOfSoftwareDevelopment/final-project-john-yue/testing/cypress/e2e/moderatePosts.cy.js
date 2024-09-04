describe("Cypress e2e tests for Feature 8: Moderating Posts", () => {
	beforeEach(() => {
		cy.exec("node ../server/controller/destroy.js");
		cy.exec("node ../server/controller/init.js");
	});

	it("Can't moderate if not logged in", () => {
		cy.visit("http://localhost:3000");

		cy.get('.postTitle').first().click();

		cy.contains("Lock Question").should('not.exist');
		cy.contains("Delete Question").should('not.exist');
		cy.get("input[placeholder=\"Enter lock reason\"]").should('not.exist');
	});

	it("Can't moderate if normal user", () => {
		cy.visit("http://localhost:3000");

		cy.get("#sideBarNav")
			.contains("User Profile")
			.click();

		cy.get("#loginUsernameInput").should('be.visible').type("Joji John");
		cy.get("#loginPasswordInput").should('be.visible').type("Joji John");

		cy.contains("Login").click();

		cy.get("#sideBarNav")
			.contains("Questions")
			.click();

		cy.get('.postTitle').first().click();

		cy.contains("Lock Question").should('not.exist');
		cy.contains("Delete Question").should('not.exist');
		cy.get("input[placeholder=\"Enter lock reason\"]").should('not.exist');
	});

	it("Can Lock Question", () => {
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

		cy.get('.postTitle').first().click();

		cy.contains("Object storage for a web application").should('be.visible');

		cy.get("input[placeholder=\"Enter lock reason\"]").should('be.visible').type("This is a test lock reason");
		cy.contains("Lock Question").click();

		cy.contains("Unlock Question").should('be.visible');
		cy.contains("This is a test lock reason").should('be.visible');
		cy.contains("Locked by saltyPeter").should('be.visible');

		cy.contains("Question is locked").should('be.visible');
		// 'force' because the button is disabled but that is exactly what we want to test
		cy.contains("Question is locked").click({force: true});
		cy.contains("Answer Text").should('not.exist');

		cy.contains("Unlock Question").click();
		cy.contains("Unlock Question").should('not.exist');
		cy.contains("This is a test lock reason").should('not.exist');
		cy.contains("Locked by saltyPeter").should('not.exist');

		cy.contains("Question is locked").should('not.exist');
		cy.contains("Lock Question").should('be.visible');
		cy.get("input[placeholder=\"Enter lock reason\"]").should('be.visible');

	})

	it("Can't lock question with no reason", () => {
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

		cy.get('.postTitle').first().click();
		cy.contains("Object storage for a web application").should('be.visible');

		cy.contains("Lock Question").click();
		cy.contains("Unlock Question").should('not.exist');
		cy.contains("Question is locked").should('not.exist');
		cy.get("input[placeholder=\"Enter lock reason\"]").should('be.visible');
	});

	it("Lock questions persists for other users", () => {
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

		cy.get('.postTitle').first().click();
		cy.contains("Object storage for a web application").should('be.visible');

		cy.get("input[placeholder=\"Enter lock reason\"]").should('be.visible').type("This is a test lock reason");
		cy.contains("Lock Question").click();

		cy.contains("User Profile").click();

		cy.contains("Logout").click();

		cy.get("#loginUsernameInput").should('be.visible').type("Joji John");
		cy.get("#loginPasswordInput").should('be.visible').type("Joji John");

		cy.contains("Login").click();

		cy.contains("Questions").click();

		cy.get('.postTitle').first().click();

		cy.contains("Unlock Question").should('not.exist');
		cy.contains("This is a test lock reason").should('be.visible');
		cy.contains("Locked by saltyPeter").should('be.visible');

		cy.contains("Question is locked").should('be.visible');
		cy.contains("Answer Questions").should('not.exist');
	});

	it("Can delete question", () => {
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

		cy.get('.postTitle').first().click();
		cy.contains("Object storage for a web application").should('be.visible');

		cy.contains("Delete Question").click();

		cy.contains("Are you sure you want to delete this question? This cannot be undone.")
		cy.contains("Confirm")
		cy.contains("Cancel")

		cy.contains("Confirm").click();

		cy.get("#right_main").children().should('have.length', 1);
		cy.get("#right_main").children().should('have.class', 'successMessage');

		cy.contains("Question deleted successfully").should('be.visible');

		cy.contains("Questions").click();

		cy.get('.postTitle').should('have.length', 2);

		cy.contains("Object storage for a web application").should('not.exist')
	});

	it("Delete questions persists for other users", () => {
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

		cy.get('.postTitle').first().click();
		cy.contains("Object storage for a web application").should('be.visible');

		cy.contains("Delete Question").click();

		cy.contains("Confirm").click();

		cy.contains("User Profile").click();

		cy.contains("Logout").click();

		cy.get("#loginUsernameInput").should('be.visible').type("Joji John");
		cy.get("#loginPasswordInput").should('be.visible').type("Joji John");

		cy.contains("Login").click();

		cy.contains("Questions").click();

		cy.get('.postTitle').should('have.length', 2);

		cy.contains("Object storage for a web application").should('not.exist')
	});
});
