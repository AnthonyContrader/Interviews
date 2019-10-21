import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { CompanyComponentsPage, CompanyUpdatePage } from './company.page-object';

describe('Company e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let companyUpdatePage: CompanyUpdatePage;
    let companyComponentsPage: CompanyComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Companies', async () => {
        await navBarPage.goToEntity('company');
        companyComponentsPage = new CompanyComponentsPage();
        expect(await companyComponentsPage.getTitle()).toMatch(/gatewayApp.interviews2Company.home.title/);
    });

    it('should load create Company page', async () => {
        await companyComponentsPage.clickOnCreateButton();
        companyUpdatePage = new CompanyUpdatePage();
        expect(await companyUpdatePage.getPageTitle()).toMatch(/gatewayApp.interviews2Company.home.createOrEditLabel/);
        await companyUpdatePage.cancel();
    });

    it('should create and save Companies', async () => {
        await companyComponentsPage.clickOnCreateButton();
        await companyUpdatePage.setNameInput('name');
        expect(await companyUpdatePage.getNameInput()).toMatch('name');
        await companyUpdatePage.setAddressInput('address');
        expect(await companyUpdatePage.getAddressInput()).toMatch('address');
        await companyUpdatePage.setCityInput('city');
        expect(await companyUpdatePage.getCityInput()).toMatch('city');
        await companyUpdatePage.setSectorInput('sector');
        expect(await companyUpdatePage.getSectorInput()).toMatch('sector');
        await companyUpdatePage.save();
        expect(await companyUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
