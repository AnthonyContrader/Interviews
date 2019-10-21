import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IRecruiter } from 'app/shared/model/interviews1/recruiter.model';
import { RecruiterService } from './recruiter.service';

@Component({
    selector: 'jhi-recruiter-update',
    templateUrl: './recruiter-update.component.html'
})
export class RecruiterUpdateComponent implements OnInit {
    private _recruiter: IRecruiter;
    isSaving: boolean;

    constructor(private recruiterService: RecruiterService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ recruiter }) => {
            this.recruiter = recruiter;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.recruiter.id !== undefined) {
            this.subscribeToSaveResponse(this.recruiterService.update(this.recruiter));
        } else {
            this.subscribeToSaveResponse(this.recruiterService.create(this.recruiter));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRecruiter>>) {
        result.subscribe((res: HttpResponse<IRecruiter>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get recruiter() {
        return this._recruiter;
    }

    set recruiter(recruiter: IRecruiter) {
        this._recruiter = recruiter;
    }
}
