import { element, by, ElementFinder } from 'protractor';

export class QuestionComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-question div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class QuestionUpdatePage {
    pageTitle = element(by.id('jhi-question-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    questionInput = element(by.id('field_question'));
    topicInput = element(by.id('field_topic'));
    recruiterSelect = element(by.id('field_recruiter'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setQuestionInput(question) {
        await this.questionInput.sendKeys(question);
    }

    async getQuestionInput() {
        return this.questionInput.getAttribute('value');
    }

    async setTopicInput(topic) {
        await this.topicInput.sendKeys(topic);
    }

    async getTopicInput() {
        return this.topicInput.getAttribute('value');
    }

    async recruiterSelectLastOption() {
        await this.recruiterSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async recruiterSelectOption(option) {
        await this.recruiterSelect.sendKeys(option);
    }

    getRecruiterSelect(): ElementFinder {
        return this.recruiterSelect;
    }

    async getRecruiterSelectedOption() {
        return this.recruiterSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}
