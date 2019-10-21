import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRecruiter } from 'app/shared/model/interviews1/recruiter.model';

@Component({
    selector: 'jhi-recruiter-detail',
    templateUrl: './recruiter-detail.component.html'
})
export class RecruiterDetailComponent implements OnInit {
    recruiter: IRecruiter;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ recruiter }) => {
            this.recruiter = recruiter;
        });
    }

    previousState() {
        window.history.back();
    }
}
