import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { GatewayCompanyModule as Interviews2CompanyModule } from './interviews2/company/company.module';
import { GatewayRecruiterModule as Interviews1RecruiterModule } from './interviews1/recruiter/recruiter.module';
import { GatewayQuestionModule as Interviews1QuestionModule } from './interviews1/question/question.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        Interviews2CompanyModule,
        Interviews1RecruiterModule,
        Interviews1QuestionModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayEntityModule {}
