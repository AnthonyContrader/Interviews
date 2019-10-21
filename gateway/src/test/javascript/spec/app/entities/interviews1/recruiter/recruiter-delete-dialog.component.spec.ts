/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GatewayTestModule } from '../../../../test.module';
import { RecruiterDeleteDialogComponent } from 'app/entities/interviews1/recruiter/recruiter-delete-dialog.component';
import { RecruiterService } from 'app/entities/interviews1/recruiter/recruiter.service';

describe('Component Tests', () => {
    describe('Recruiter Management Delete Component', () => {
        let comp: RecruiterDeleteDialogComponent;
        let fixture: ComponentFixture<RecruiterDeleteDialogComponent>;
        let service: RecruiterService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [RecruiterDeleteDialogComponent]
            })
                .overrideTemplate(RecruiterDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RecruiterDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RecruiterService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
