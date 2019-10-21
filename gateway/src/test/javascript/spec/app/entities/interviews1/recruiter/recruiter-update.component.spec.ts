/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { RecruiterUpdateComponent } from 'app/entities/interviews1/recruiter/recruiter-update.component';
import { RecruiterService } from 'app/entities/interviews1/recruiter/recruiter.service';
import { Recruiter } from 'app/shared/model/interviews1/recruiter.model';

describe('Component Tests', () => {
    describe('Recruiter Management Update Component', () => {
        let comp: RecruiterUpdateComponent;
        let fixture: ComponentFixture<RecruiterUpdateComponent>;
        let service: RecruiterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [RecruiterUpdateComponent]
            })
                .overrideTemplate(RecruiterUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RecruiterUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RecruiterService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Recruiter(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.recruiter = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Recruiter();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.recruiter = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
