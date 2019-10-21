import { element, by, ElementFinder } from 'protractor';

export class RecruiterComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-recruiter div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class RecruiterUpdatePage {
    pageTitle = element(by.id('jhi-recruiter-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    companyIdInput = element(by.id('field_companyId'));
    companyNameInput = element(by.id('field_companyName'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNameInput(name) {
        await this.nameInput.sendKeys(name);
    }

    async getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    async setCompanyIdInput(companyId) {
        await this.companyIdInput.sendKeys(companyId);
    }

    async getCompanyIdInput() {
        return this.companyIdInput.getAttribute('value');
    }

    async setCompanyNameInput(companyName) {
        await this.companyNameInput.sendKeys(companyName);
    }

    async getCompanyNameInput() {
        return this.companyNameInput.getAttribute('value');
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
