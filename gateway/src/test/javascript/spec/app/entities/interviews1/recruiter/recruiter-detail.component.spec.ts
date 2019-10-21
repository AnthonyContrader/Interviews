/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { RecruiterDetailComponent } from 'app/entities/interviews1/recruiter/recruiter-detail.component';
import { Recruiter } from 'app/shared/model/interviews1/recruiter.model';

describe('Component Tests', () => {
    describe('Recruiter Management Detail Component', () => {
        let comp: RecruiterDetailComponent;
        let fixture: ComponentFixture<RecruiterDetailComponent>;
        const route = ({ data: of({ recruiter: new Recruiter(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [RecruiterDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RecruiterDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RecruiterDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.recruiter).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
