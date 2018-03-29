import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Expedicionuser } from './expedicionuser.model';
import { ExpedicionuserPopupService } from './expedicionuser-popup.service';
import { ExpedicionuserService } from './expedicionuser.service';

@Component({
    selector: 'jhi-expedicionuser-delete-dialog',
    templateUrl: './expedicionuser-delete-dialog.component.html'
})
export class ExpedicionuserDeleteDialogComponent {

    expedicionuser: Expedicionuser;

    constructor(
        private expedicionuserService: ExpedicionuserService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.expedicionuserService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'expedicionuserListModification',
                content: 'Deleted an expedicionuser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-expedicionuser-delete-popup',
    template: ''
})
export class ExpedicionuserDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private expedicionuserPopupService: ExpedicionuserPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.expedicionuserPopupService
                .open(ExpedicionuserDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
