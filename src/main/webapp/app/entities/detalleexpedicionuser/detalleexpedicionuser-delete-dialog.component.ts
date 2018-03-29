import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Detalleexpedicionuser } from './detalleexpedicionuser.model';
import { DetalleexpedicionuserPopupService } from './detalleexpedicionuser-popup.service';
import { DetalleexpedicionuserService } from './detalleexpedicionuser.service';

@Component({
    selector: 'jhi-detalleexpedicionuser-delete-dialog',
    templateUrl: './detalleexpedicionuser-delete-dialog.component.html'
})
export class DetalleexpedicionuserDeleteDialogComponent {

    detalleexpedicionuser: Detalleexpedicionuser;

    constructor(
        private detalleexpedicionuserService: DetalleexpedicionuserService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.detalleexpedicionuserService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'detalleexpedicionuserListModification',
                content: 'Deleted an detalleexpedicionuser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-detalleexpedicionuser-delete-popup',
    template: ''
})
export class DetalleexpedicionuserDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private detalleexpedicionuserPopupService: DetalleexpedicionuserPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.detalleexpedicionuserPopupService
                .open(DetalleexpedicionuserDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
