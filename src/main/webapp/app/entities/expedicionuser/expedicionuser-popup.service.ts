import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Expedicionuser } from './expedicionuser.model';
import { ExpedicionuserService } from './expedicionuser.service';

@Injectable()
export class ExpedicionuserPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private expedicionuserService: ExpedicionuserService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.expedicionuserService.find(id).subscribe((expedicionuser) => {
                    if (expedicionuser.fechaRegistro) {
                        expedicionuser.fechaRegistro = {
                            year: expedicionuser.fechaRegistro.getFullYear(),
                            month: expedicionuser.fechaRegistro.getMonth() + 1,
                            day: expedicionuser.fechaRegistro.getDate()
                        };
                    }
                    this.ngbModalRef = this.expedicionuserModalRef(component, expedicionuser);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.expedicionuserModalRef(component, new Expedicionuser());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    expedicionuserModalRef(component: Component, expedicionuser: Expedicionuser): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.expedicionuser = expedicionuser;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
