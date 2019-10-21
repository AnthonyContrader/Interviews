import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICompany } from 'app/shared/model/interviews2/company.model';

@Component({
    selector: 'jhi-company-detail',
    templateUrl: './company-detail.component.html'
})
export class CompanyDetailComponent implements OnInit {
    company: ICompany;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ company }) => {
            this.company = company;
        });
    }

    previousState() {
        window.history.back();
    }
}
