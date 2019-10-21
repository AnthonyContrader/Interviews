import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { QuestionComponentsPage, QuestionUpdatePage } from './question.page-object';

describe('Question e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let questionUpdatePage: QuestionUpdatePage;
    let questionComponentsPage: QuestionComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Questions', async () => {
        await navBarPage.goToEntity('question');
        questionComponentsPage = new QuestionComponentsPage();
        expect(await questionComponentsPage.getTitle()).toMatch(/gatewayApp.interviews1Question.home.title/);
    });

    it('should load create Question page', async () => {
        await questionComponentsPage.clickOnCreateButton();
        questionUpdatePage = new QuestionUpdatePage();
        expect(await questionUpdatePage.getPageTitle()).toMatch(/gatewayApp.interviews1Question.home.createOrEditLabel/);
        await questionUpdatePage.cancel();
    });

    /* it('should create and save Questions', async () => {
        await questionComponentsPage.clickOnCreateButton();
        await questionUpdatePage.setQuestionInput('question');
        expect(await questionUpdatePage.getQuestionInput()).toMatch('question');
        await questionUpdatePage.setTopicInput('topic');
        expect(await questionUpdatePage.getTopicInput()).toMatch('topic');
        await questionUpdatePage.recruiterSelectLastOption();
        await questionUpdatePage.save();
        expect(await questionUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });*/

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
