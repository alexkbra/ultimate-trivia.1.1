import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Detalleexpedicionuser } from './detalleexpedicionuser.model';
import { DetalleexpedicionuserPopupService } from './detalleexpedicionuser-popup.service';
import { DetalleexpedicionuserService } from './detalleexpedicionuser.service';
import { Pregunta, PreguntaService } from '../pregunta';
import { Expedicionuser, ExpedicionuserService } from '../expedicionuser';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-detalleexpedicionuser-dialog',
    templateUrl: './detalleexpedicionuser-dialog.component.html'
})
export class DetalleexpedicionuserDialogComponent implements OnInit {

    detalleexpedicionuser: Detalleexpedicionuser;
    isSaving: boolean;

    preguntas: Pregunta[];

    expedicionusers: Expedicionuser[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private detalleexpedicionuserService: DetalleexpedicionuserService,
        private preguntaService: PreguntaService,
        private expedicionuserService: ExpedicionuserService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.preguntaService.query()
            .subscribe((res: ResponseWrapper) => { this.preguntas = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.expedicionuserService.query()
            .subscribe((res: ResponseWrapper) => { this.expedicionusers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.detalleexpedicionuser.id !== undefined) {
            this.subscribeToSaveResponse(
                this.detalleexpedicionuserService.update(this.detalleexpedicionuser));
        } else {
            this.subscribeToSaveResponse(
                this.detalleexpedicionuserService.create(this.detalleexpedicionuser));
        }
    }

    private subscribeToSaveResponse(result: Observable<Detalleexpedicionuser>) {
        result.subscribe((res: Detalleexpedicionuser) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Detalleexpedicionuser) {
        this.eventManager.broadcast({ name: 'detalleexpedicionuserListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackPreguntaById(index: number, item: Pregunta) {
        return item.id;
    }

    trackExpedicionuserById(index: number, item: Expedicionuser) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-detalleexpedicionuser-popup',
    template: ''
})
export class DetalleexpedicionuserPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private detalleexpedicionuserPopupService: DetalleexpedicionuserPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.detalleexpedicionuserPopupService
                    .open(DetalleexpedicionuserDialogComponent as Component, params['id']);
            } else {
                this.detalleexpedicionuserPopupService
                    .open(DetalleexpedicionuserDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
