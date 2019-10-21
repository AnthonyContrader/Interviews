import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IQuestion } from 'app/shared/model/interviews1/question.model';
import { QuestionService } from './question.service';
import { IRecruiter } from 'app/shared/model/interviews1/recruiter.model';
import { RecruiterService } from 'app/entities/interviews1/recruiter';

@Component({
    selector: 'jhi-question-update',
    templateUrl: './question-update.component.html'
})
export class QuestionUpdateComponent implements OnInit {
    private _question: IQuestion;
    isSaving: boolean;

    recruiters: IRecruiter[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private questionService: QuestionService,
        private recruiterService: RecruiterService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ question }) => {
            this.question = question;
        });
        this.recruiterService.query().subscribe(
            (res: HttpResponse<IRecruiter[]>) => {
                this.recruiters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.question.id !== undefined) {
            this.subscribeToSaveResponse(this.questionService.update(this.question));
        } else {
            this.subscribeToSaveResponse(this.questionService.create(this.question));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IQuestion>>) {
        result.subscribe((res: HttpResponse<IQuestion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackRecruiterById(index: number, item: IRecruiter) {
        return item.id;
    }
    get question() {
        return this._question;
    }

    set question(question: IQuestion) {
        this._question = question;
    }
}
