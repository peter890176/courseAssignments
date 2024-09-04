const postTitlesNewestOrder = [
	'Object storage for a web application',
	'android studio save string shared preference, start activity and load the saved string',
	'Programmatically navigate using React router'
];

const postTitlesActiveOrder = [
	'Programmatically navigate using React router',
	'android studio save string shared preference, start activity and load the saved string',
	'Object storage for a web application'
];

const postTitlesNewestOrderJavascriptTag = [
	'android studio save string shared preference, start activity and load the saved string',
	'Programmatically navigate using React router'
];

const postTitlesActiveOrderJavascriptTag = [
	'Programmatically navigate using React router',
	'android studio save string shared preference, start activity and load the saved string'
];

const postTitlesNewestOrderStorageAndroidStudioTags = [
	'Object storage for a web application',
	'android studio save string shared preference, start activity and load the saved string'
];

const postTitlesActiveOrderStorageAndroidStudioTags = [
	'android studio save string shared preference, start activity and load the saved string',
	'Object storage for a web application'
];

const postTitlesActiveOrderRouterStorageKeywords = [
	'Programmatically navigate using React router',
	'Object storage for a web application'
];

const postTitlesNewestOrderRouterStorageKeywords = [
	'Object storage for a web application',
	'Programmatically navigate using React router'
];

describe("Cypress e2e tests for Feature 3: Searching Posts", () => {
	beforeEach(() => {
		cy.exec("node ../server/controller/destroy.js");
		cy.exec("node ../server/controller/init.js");
	});
	
	it("re-searching and re-ordering multiple times", () => {
		cy.visit("http://localhost:3000");

		cy.get("input[placeholder=\"Search ...\"]").type("{enter}");

		cy.contains("Search Results")

		cy.get(".postTitle").should("have.length", 3)
		cy.get(".postTitle").each(($el, index) => {
			cy.wrap($el).should("contain", postTitlesNewestOrder[index])
		})

		// Re-order posts by Active
		cy.contains('Active').click()

		cy.get(".postTitle").should("have.length", 3)
		cy.get(".postTitle").each(($el, index) => {
			cy.wrap($el).should("contain", postTitlesActiveOrder[index])
		})

		// Search for posts with the [javascript] tag
		cy.get("input[placeholder=\"Search ...\"]").type("[javascript]{enter}");

		cy.contains("Search Results")

		cy.get(".postTitle").should("have.length", 2)
		cy.get(".postTitle").each(($el, index) => {
			cy.wrap($el).should("contain", postTitlesActiveOrderJavascriptTag[index])
		})

		// Re-order posts by Newest
		cy.contains('Newest').click()

		cy.get(".postTitle").should("have.length", 2)
		cy.get(".postTitle").each(($el, index) => {
			cy.wrap($el).should("contain", postTitlesNewestOrderJavascriptTag[index])
		})

		// Search with two tags [storage] and [android-studio]
		cy.get("input[placeholder=\"Search ...\"]").clear().type("[storage] [android-studio]{enter}");

		cy.contains("Search Results")

		cy.get(".postTitle").should("have.length", 2)
		cy.get(".postTitle").each(($el, index) => {
			cy.wrap($el).should("contain", postTitlesNewestOrderStorageAndroidStudioTags[index])
		})

		// Re-order posts by Active
		cy.contains('Active').click()

		cy.get(".postTitle").should("have.length", 2)
		cy.get(".postTitle").each(($el, index) => {
			cy.wrap($el).should("contain", postTitlesActiveOrderStorageAndroidStudioTags[index])
		})

		// Search with a keyword "router"
		cy.get("input[placeholder=\"Search ...\"]").clear().type("router{enter}");

		cy.contains("Search Results")

		cy.get(".postTitle").should("have.length", 1)
		cy.get(".postTitle").contains("Programmatically navigate using React router")

		// Search with two keywords "router" and "storage"
		cy.get("input[placeholder=\"Search ...\"]").clear().type("router storage{enter}");

		cy.contains("Search Results")

		cy.get(".postTitle").should("have.length", 2)
		cy.get(".postTitle").each(($el, index) => {
			cy.wrap($el).should("contain", postTitlesActiveOrderRouterStorageKeywords[index])
		})

		// Re-order posts by Newest
		cy.contains('Newest').click()

		cy.get(".postTitle").should("have.length", 2)
		cy.get(".postTitle").each(($el, index) => {
			cy.wrap($el).should("contain", postTitlesNewestOrderRouterStorageKeywords[index])
		})

		// Search with one keyword "storage" and one tag [javascript]
		cy.get("input[placeholder=\"Search ...\"]").clear().type("storage [javascript]{enter}");

		cy.contains("Search Results")
		
		cy.get(".postTitle").should("have.length", 3)
		cy.get(".postTitle").each(($el, index) => {
			cy.wrap($el).should("contain", postTitlesNewestOrder[index])
		})

		// Re-order posts by Active
		cy.contains('Active').click()

		cy.get(".postTitle").should("have.length", 3)
		cy.get(".postTitle").each(($el, index) => {
			cy.wrap($el).should("contain", postTitlesActiveOrder[index])
		})
	});

	it("searching within post text", () => {
		cy.visit("http://localhost:3000");

		cy.get("input[placeholder=\"Search ...\"]").type("40 million{enter}");

		cy.contains("Search Results")

		cy.get(".postTitle").should("have.length", 1)
		cy.get(".postTitle").contains("Object storage for a web application")
	});

	it("Search bar works from within answer page", () => {
		cy.visit("http://localhost:3000");

		cy.get(".postTitle").first().click();

		cy.get("input[placeholder=\"Search ...\"]").type("40 million{enter}");

		cy.contains("Search Results")

		cy.get(".postTitle").should("have.length", 1)
		cy.get(".postTitle").contains("Object storage for a web application")
	});
});
