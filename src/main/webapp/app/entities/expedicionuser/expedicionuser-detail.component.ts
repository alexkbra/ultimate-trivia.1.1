import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Expedicionuser } from './expedicionuser.model';
import { ExpedicionuserService } from './expedicionuser.service';

@Component({
    selector: 'jhi-expedicionuser-detail',
    templateUrl: './expedicionuser-detail.component.html'
})
export class ExpedicionuserDetailComponent implements OnInit, OnDestroy {

    expedicionuser: Expedicionuser;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private expedicionuserService: ExpedicionuserService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInExpedicionusers();
    }

    load(id) {
        this.expedicionuserService.find(id).subscribe((expedicionuser) => {
            this.expedicionuser = expedicionuser;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInExpedicionusers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'expedicionuserListModification',
            (response) => this.load(this.expedicionuser.id)
        );
    }
}
