describe("Cypress e2e tests for Feature 4: Answering/Commenting on Posts", () => {
	beforeEach(() => {
		cy.exec('node ../server/controller/destroy.js')
		cy.exec('node ../server/controller/init.js')
	});

	it("Can't answer if not logged in", () => {
		cy.visit("http://localhost:3000");

		const stub = cy.stub()
		cy.on('window:alert', stub)

		cy.get(".postTitle").first().click();

		cy.contains("Answer Question").click().then(() => {
			expect(stub.getCall(0)).to.be.calledWith('Please login first.')
		});
	});

	it("Can answer if logged in", () => {
		cy.visit("http://localhost:3000");

		cy.get("#sideBarNav")
			.contains("User Profile")
			.click();

		cy.get("#loginUsernameInput").should('be.visible').type("saltyPeter");
		cy.get("#loginPasswordInput").should('be.visible').type("saltyPeter");

		cy.contains("Login").click();

		cy.contains("Profile for saltyPeter").should('be.visible');

		cy.get("#sideBarNav")
			.contains("Questions")
			.click();

		cy.contains("All Questions").should('be.visible');

		cy.get(".postTitle").first().click();
		cy.contains("Object storage for a web application").should('be.visible');

		cy.contains(/\d* answers/)
			.invoke('text')
			.then((text) => {
				const initialNumAnswers = parseInt(text.match(/\d*/)[0]);

				cy.contains("Answer Question").click();

				cy.contains("Answer Text").should('be.visible');

				cy.get("#answerTextInput").type("This is a test answer");

				cy.contains("Post Answer").click();

				cy.contains(`${initialNumAnswers + 1} answers`)
					.should('be.visible')
					.invoke('text')
					.then((text) => {
						const finalNumAnswers = parseInt(text.match(/\d*/)[0]);

						expect(finalNumAnswers).to.equal(initialNumAnswers + 1);
					})

				cy.get(".answerText").first().should("contain", "This is a test answer")
			})

	});

	it("Can't post an answer with no text", () => {
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

		cy.get(".postTitle").first().click();

		cy.contains("Answer Question").click();

		cy.contains("Answer Text").should('be.visible');

		cy.contains("Post Answer").click();

		cy.contains("Answer text cannot be empty")
	});
});

