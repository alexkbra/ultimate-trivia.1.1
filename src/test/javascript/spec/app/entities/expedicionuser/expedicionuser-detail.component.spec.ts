/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { TriviaTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ExpedicionuserDetailComponent } from '../../../../../../main/webapp/app/entities/expedicionuser/expedicionuser-detail.component';
import { ExpedicionuserService } from '../../../../../../main/webapp/app/entities/expedicionuser/expedicionuser.service';
import { Expedicionuser } from '../../../../../../main/webapp/app/entities/expedicionuser/expedicionuser.model';

describe('Component Tests', () => {

    describe('Expedicionuser Management Detail Component', () => {
        let comp: ExpedicionuserDetailComponent;
        let fixture: ComponentFixture<ExpedicionuserDetailComponent>;
        let service: ExpedicionuserService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [TriviaTestModule],
                declarations: [ExpedicionuserDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ExpedicionuserService,
                    JhiEventManager
                ]
            }).overrideTemplate(ExpedicionuserDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ExpedicionuserDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExpedicionuserService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Expedicionuser(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.expedicionuser).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
