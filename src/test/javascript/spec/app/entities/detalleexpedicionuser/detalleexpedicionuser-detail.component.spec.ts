/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { TriviaTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { DetalleexpedicionuserDetailComponent } from '../../../../../../main/webapp/app/entities/detalleexpedicionuser/detalleexpedicionuser-detail.component';
import { DetalleexpedicionuserService } from '../../../../../../main/webapp/app/entities/detalleexpedicionuser/detalleexpedicionuser.service';
import { Detalleexpedicionuser } from '../../../../../../main/webapp/app/entities/detalleexpedicionuser/detalleexpedicionuser.model';

describe('Component Tests', () => {

    describe('Detalleexpedicionuser Management Detail Component', () => {
        let comp: DetalleexpedicionuserDetailComponent;
        let fixture: ComponentFixture<DetalleexpedicionuserDetailComponent>;
        let service: DetalleexpedicionuserService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [TriviaTestModule],
                declarations: [DetalleexpedicionuserDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    DetalleexpedicionuserService,
                    JhiEventManager
                ]
            }).overrideTemplate(DetalleexpedicionuserDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DetalleexpedicionuserDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DetalleexpedicionuserService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Detalleexpedicionuser(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.detalleexpedicionuser).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
