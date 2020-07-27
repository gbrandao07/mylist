export class Card {
    id : string;
	name: string;
	authorName: string;
	description: string;

	constructor(formCardName: string, formCardAuthorName: string, formCardDescription: string) {
		this.name = formCardName;
		this.authorName = formCardAuthorName;
		this.description = formCardDescription
	}
}