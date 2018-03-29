import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Empresas } from './empresas.model';
import { EmpresasService } from './empresas.service';

@Injectable()
export class EmpresasPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private empresasService: EmpresasService

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
                this.empresasService.find(id).subscribe((empresas) => {
                    if (empresas.fechaRegistro) {
                        empresas.fechaRegistro = {
                            year: empresas.fechaRegistro.getFullYear(),
                            month: empresas.fechaRegistro.getMonth() + 1,
                            day: empresas.fechaRegistro.getDate()
                        };
                    }
                    this.ngbModalRef = this.empresasModalRef(component, empresas);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.empresasModalRef(component, new Empresas());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    empresasModalRef(component: Component, empresas: Empresas): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.empresas = empresas;
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
