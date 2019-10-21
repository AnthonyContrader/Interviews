import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Recruiter } from 'app/shared/model/interviews1/recruiter.model';
import { RecruiterService } from './recruiter.service';
import { RecruiterComponent } from './recruiter.component';
import { RecruiterDetailComponent } from './recruiter-detail.component';
import { RecruiterUpdateComponent } from './recruiter-update.component';
import { RecruiterDeletePopupComponent } from './recruiter-delete-dialog.component';
import { IRecruiter } from 'app/shared/model/interviews1/recruiter.model';

@Injectable({ providedIn: 'root' })
export class RecruiterResolve implements Resolve<IRecruiter> {
    constructor(private service: RecruiterService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((recruiter: HttpResponse<Recruiter>) => recruiter.body));
        }
        return of(new Recruiter());
    }
}

export const recruiterRoute: Routes = [
    {
        path: 'recruiter',
        component: RecruiterComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'gatewayApp.interviews1Recruiter.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'recruiter/:id/view',
        component: RecruiterDetailComponent,
        resolve: {
            recruiter: RecruiterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.interviews1Recruiter.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'recruiter/new',
        component: RecruiterUpdateComponent,
        resolve: {
            recruiter: RecruiterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.interviews1Recruiter.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'recruiter/:id/edit',
        component: RecruiterUpdateComponent,
        resolve: {
            recruiter: RecruiterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.interviews1Recruiter.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const recruiterPopupRoute: Routes = [
    {
        path: 'recruiter/:id/delete',
        component: RecruiterDeletePopupComponent,
        resolve: {
            recruiter: RecruiterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'gatewayApp.interviews1Recruiter.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
