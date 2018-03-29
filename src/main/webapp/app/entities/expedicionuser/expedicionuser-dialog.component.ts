import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Expedicionuser } from './expedicionuser.model';
import { ExpedicionuserPopupService } from './expedicionuser-popup.service';
import { ExpedicionuserService } from './expedicionuser.service';
import { User, UserService } from '../../shared';
import { Expedicion, ExpedicionService } from '../expedicion';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-expedicionuser-dialog',
    templateUrl: './expedicionuser-dialog.component.html'
})
export class ExpedicionuserDialogComponent implements OnInit {

    expedicionuser: Expedicionuser;
    isSaving: boolean;

    users: User[];

    expedicions: Expedicion[];
    fechaRegistroDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private expedicionuserService: ExpedicionuserService,
        private userService: UserService,
        private expedicionService: ExpedicionService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.userService.query()
            .subscribe((res: ResponseWrapper) => { this.users = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.expedicionService.query()
            .subscribe((res: ResponseWrapper) => { this.expedicions = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.expedicionuser.id !== undefined) {
            this.subscribeToSaveResponse(
                this.expedicionuserService.update(this.expedicionuser));
        } else {
            this.subscribeToSaveResponse(
                this.expedicionuserService.create(this.expedicionuser));
        }
    }

    private subscribeToSaveResponse(result: Observable<Expedicionuser>) {
        result.subscribe((res: Expedicionuser) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Expedicionuser) {
        this.eventManager.broadcast({ name: 'expedicionuserListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackUserById(index: number, item: User) {
        return item.id;
    }

    trackExpedicionById(index: number, item: Expedicion) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-expedicionuser-popup',
    template: ''
})
export class ExpedicionuserPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private expedicionuserPopupService: ExpedicionuserPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.expedicionuserPopupService
                    .open(ExpedicionuserDialogComponent as Component, params['id']);
            } else {
                this.expedicionuserPopupService
                    .open(ExpedicionuserDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
