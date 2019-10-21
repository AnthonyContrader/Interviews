import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared';
import {
    RecruiterComponent,
    RecruiterDetailComponent,
    RecruiterUpdateComponent,
    RecruiterDeletePopupComponent,
    RecruiterDeleteDialogComponent,
    recruiterRoute,
    recruiterPopupRoute
} from './';

const ENTITY_STATES = [...recruiterRoute, ...recruiterPopupRoute];

@NgModule({
    imports: [GatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RecruiterComponent,
        RecruiterDetailComponent,
        RecruiterUpdateComponent,
        RecruiterDeleteDialogComponent,
        RecruiterDeletePopupComponent
    ],
    entryComponents: [RecruiterComponent, RecruiterUpdateComponent, RecruiterDeleteDialogComponent, RecruiterDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayRecruiterModule {}
