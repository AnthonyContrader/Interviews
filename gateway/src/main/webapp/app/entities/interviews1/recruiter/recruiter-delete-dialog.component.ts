import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRecruiter } from 'app/shared/model/interviews1/recruiter.model';
import { RecruiterService } from './recruiter.service';

@Component({
    selector: 'jhi-recruiter-delete-dialog',
    templateUrl: './recruiter-delete-dialog.component.html'
})
export class RecruiterDeleteDialogComponent {
    recruiter: IRecruiter;

    constructor(private recruiterService: RecruiterService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.recruiterService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'recruiterListModification',
                content: 'Deleted an recruiter'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-recruiter-delete-popup',
    template: ''
})
export class RecruiterDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ recruiter }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RecruiterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.recruiter = recruiter;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
