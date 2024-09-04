describe("Cypress e2e tests for Feature 1: Viewing Posts", () => {
	beforeEach(() => {
		cy.exec("node ../server/controller/destroy.js");
		cy.exec("node ../server/controller/init.js");
	});

	it("Can navigate to a post", () => {
		cy.visit("http://localhost:3000");

		cy.contains(/\d* views/).invoke("text").then((views) => {
			const viewCount = parseInt(views);
			cy.get(".postTitle").first().click();

			cy.get(".answer_question_title").should("contain", "Object storage for a web application")
			cy.get(".answer").first().should("contain", "Storing content as BLOBs in databases")

			cy.contains(`${viewCount + 1} views`).should("be.visible");

			cy.contains("Questions").click();

			cy.contains(`${viewCount + 1} views`).should("be.visible");
		});

	});

	it("Can navigate to multiple posts", () => {
		cy.visit("http://localhost:3000");

		cy.get(".postTitle").first().click();
		cy.get(".answer_question_title").should("contain", "Object storage for a web application")
		cy.get(".answer").first().should("contain", "Storing content as BLOBs in databases")

		cy.get("#sideBarNav")
			.find(".menu_button")
			.contains("Questions")
			.click();

		cy.get("#question_list")
			.find(".postTitle")
			.contains("android studio save string shared preference, start activity and load the saved string")
			.click();

		cy.get(".answer_question_title").should("contain", "android studio save string shared preference, start activity and load the saved string")
	})

});
