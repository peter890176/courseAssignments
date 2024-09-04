describe("Cypress e2e tests for Feature 5: Voting on Posts", () => {
	beforeEach(() => {
		cy.exec("node ../server/controller/destroy.js");
		cy.exec("node ../server/controller/init.js");
	});

	it("Can't vote if not logged in", () => {
		cy.visit("http://localhost:3000");
		cy.contains("All Questions").should('be.visible');

		const stub = cy.stub()
		cy.on('window:alert', stub)

		cy.get(".postTitle").first().click();

		cy.get("#up-vote").first().click().then(() => {
			expect(stub.getCall(0)).to.be.calledWith('Please login to vote.')
		});

		cy.get("#down-vote").first().click().then(() => {
			expect(stub.getCall(1)).to.be.calledWith('Please login to vote.')
		});
	});

	it("Can vote exactly once if logged in", () => {
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

		cy.contains(/\d* votes/).first().invoke('text').then((text) => {
			const questionPageInitialVotes = parseInt(text.match(/\d*/)[0]);

			cy.get(".postTitle").first().click();

			cy.contains("Object storage for a web application").should('be.visible');

			cy.contains(/\d* votes/).first().invoke('text').then((text) => {
				const answerPageInitialVotes = parseInt(text.match(/\d*/)[0]);

				cy.get("#up-vote").first().click();

				cy.contains(`${answerPageInitialVotes + 1} votes`).first().invoke('text').then((text) => {
					const answerPageFinalVotes = parseInt(text.match(/\d*/)[0]);

					expect(answerPageFinalVotes).to.equal(answerPageInitialVotes + 1);
				});

				cy.get("#up-vote").first().click();

				cy.contains(`${answerPageInitialVotes + 1} votes`).first().invoke('text').then((text) => {
					const answerPageFinalVotes = parseInt(text.match(/\d*/)[0]);

					expect(answerPageFinalVotes).to.equal(answerPageInitialVotes + 1);
				});

				cy.get("#down-vote").first().click();

				cy.contains(`${answerPageInitialVotes} votes`).first().invoke('text').then((text) => {
					const answerPageFinalVotes = parseInt(text.match(/\d*/)[0]);

					expect(answerPageFinalVotes).to.equal(answerPageInitialVotes);
				});

				cy.get("#down-vote").first().click();

				cy.contains(`${answerPageInitialVotes -1} votes`).first().invoke('text').then((text) => {
					const answerPageFinalVotes = parseInt(text.match(/-?\d*/)[0]);

					expect(answerPageFinalVotes).to.equal(answerPageInitialVotes - 1);
				});

				cy.get("#down-vote").first().click();

				cy.contains(`${answerPageInitialVotes -1} votes`).first().invoke('text').then((text) => {
					const answerPageFinalVotes = parseInt(text.match(/-?\d*/)[0]);

					expect(answerPageFinalVotes).to.equal(answerPageInitialVotes - 1);
				});

				cy.contains("Questions").click();

				cy.contains(`${answerPageInitialVotes -1} votes`).first().invoke('text').then((text) => {
					const questionPageFinalVotes = parseInt(text.match(/-?\d*/)[0]);

					expect(questionPageFinalVotes).to.equal(questionPageInitialVotes - 1);
				});

				cy.contains("User Profile").click();

				cy.contains("Questions").click();

				cy.contains(/\d* votes/).first().invoke('text').then((text) => {
					const questionPageFinalVotes = parseInt(text.match(/-?\d*/)[0]);

					expect(questionPageFinalVotes).to.equal(questionPageInitialVotes - 1);
				});
			});
		});
	})

	it("Changing Profile Information Doesn't Allow More Votes", () => {
		cy.visit("http://localhost:3000");

		cy.contains("User Profile").click();

		cy.get("#loginUsernameInput").should('be.visible').type("saltyPeter");
		cy.get("#loginPasswordInput").should('be.visible').type("saltyPeter");

		cy.contains("Login").click();

		cy.contains("Questions").click();

		cy.get(".postTitle").first().click();

		cy.contains("0 votes").should('be.visible');

		cy.get("#up-vote").click();

		cy.contains("1 votes").should('be.visible');

		cy.contains("User Profile").click();

		cy.contains("Profile for saltyPeter").should('be.visible');

		cy.contains("Edit Profile").click();

		cy.get("#username-edit").should('be.visible').clear().type("saltyPeter2");
		cy.get("#password-edit").should('be.visible').clear().type("saltyPeter2");
		cy.get("#email-edit").should('be.visible').clear().type("newEmail@email.email");

		cy.contains("Save").click();

		cy.contains("Back to Details").click();

		cy.contains("Profile for saltyPeter2").should('be.visible');

		cy.contains("Questions").click();

		cy.get(".postTitle").first().click();

		cy.contains("1 votes").should('be.visible');

		cy.get("#up-vote").click();

		cy.contains("1 votes").should('be.visible');
	});
});
