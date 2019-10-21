import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { RecruiterComponentsPage, RecruiterUpdatePage } from './recruiter.page-object';

describe('Recruiter e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let recruiterUpdatePage: RecruiterUpdatePage;
    let recruiterComponentsPage: RecruiterComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Recruiters', async () => {
        await navBarPage.goToEntity('recruiter');
        recruiterComponentsPage = new RecruiterComponentsPage();
        expect(await recruiterComponentsPage.getTitle()).toMatch(/gatewayApp.interviews1Recruiter.home.title/);
    });

    it('should load create Recruiter page', async () => {
        await recruiterComponentsPage.clickOnCreateButton();
        recruiterUpdatePage = new RecruiterUpdatePage();
        expect(await recruiterUpdatePage.getPageTitle()).toMatch(/gatewayApp.interviews1Recruiter.home.createOrEditLabel/);
        await recruiterUpdatePage.cancel();
    });

    it('should create and save Recruiters', async () => {
        await recruiterComponentsPage.clickOnCreateButton();
        await recruiterUpdatePage.setNameInput('name');
        expect(await recruiterUpdatePage.getNameInput()).toMatch('name');
        await recruiterUpdatePage.setCompanyIdInput('5');
        expect(await recruiterUpdatePage.getCompanyIdInput()).toMatch('5');
        await recruiterUpdatePage.setCompanyNameInput('companyName');
        expect(await recruiterUpdatePage.getCompanyNameInput()).toMatch('companyName');
        await recruiterUpdatePage.save();
        expect(await recruiterUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
