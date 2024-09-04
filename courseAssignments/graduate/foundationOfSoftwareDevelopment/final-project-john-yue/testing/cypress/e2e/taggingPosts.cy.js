describe("Cypress e2e tests for Feature 6: Tagging Posts", () => {
	beforeEach(() => {
		cy.exec("node ../server/controller/destroy.js");
		cy.exec("node ../server/controller/init.js");
	});

	it("viewing posts by tags", () => {
		cy.visit("http://localhost:3000");

		cy.get("#sideBarNav")
			.contains("Tags")
			.click();

		cy.contains("react").should("be.visible");
		cy.contains("javascript").should("be.visible");
		cy.contains("android-studio").should("be.visible");
		cy.contains("shared-preferences").should('be.visible');
		cy.contains("storage").should("be.visible");
		cy.contains("website").should("be.visible");

		cy.contains("react").click();

		cy.get(".postTitle").should("have.length", 1)
		cy.contains("Programmatically navigate using React router").should("be.visible");

		cy.get("#sideBarNav")
			.contains("Tags")
			.click();

		cy.contains("javascript").click();

		cy.get(".postTitle").should("have.length", 2)
		cy.contains("android studio save string shared preference, start activity and load the saved string").should("be.visible");
		cy.contains("Programmatically navigate using React router").should("be.visible");

		cy.contains("shared-preferences").click();

		cy.get(".postTitle").should("have.length", 1)
		cy.contains("android studio save string shared preference, start activity and load the saved string").should("be.visible");
	});
});
