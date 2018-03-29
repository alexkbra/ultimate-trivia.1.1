import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Detalleexpedicionuser } from './detalleexpedicionuser.model';
import { DetalleexpedicionuserService } from './detalleexpedicionuser.service';

@Component({
    selector: 'jhi-detalleexpedicionuser-detail',
    templateUrl: './detalleexpedicionuser-detail.component.html'
})
export class DetalleexpedicionuserDetailComponent implements OnInit, OnDestroy {

    detalleexpedicionuser: Detalleexpedicionuser;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private detalleexpedicionuserService: DetalleexpedicionuserService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDetalleexpedicionusers();
    }

    load(id) {
        this.detalleexpedicionuserService.find(id).subscribe((detalleexpedicionuser) => {
            this.detalleexpedicionuser = detalleexpedicionuser;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDetalleexpedicionusers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'detalleexpedicionuserListModification',
            (response) => this.load(this.detalleexpedicionuser.id)
        );
    }
}
