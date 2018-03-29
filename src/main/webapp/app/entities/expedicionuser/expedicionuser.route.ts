import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ExpedicionuserComponent } from './expedicionuser.component';
import { ExpedicionuserDetailComponent } from './expedicionuser-detail.component';
import { ExpedicionuserPopupComponent } from './expedicionuser-dialog.component';
import { ExpedicionuserDeletePopupComponent } from './expedicionuser-delete-dialog.component';

@Injectable()
export class ExpedicionuserResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const expedicionuserRoute: Routes = [
    {
        path: 'expedicionuser',
        component: ExpedicionuserComponent,
        resolve: {
            'pagingParams': ExpedicionuserResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'triviaApp.expedicionuser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'expedicionuser/:id',
        component: ExpedicionuserDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'triviaApp.expedicionuser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const expedicionuserPopupRoute: Routes = [
    {
        path: 'expedicionuser-new',
        component: ExpedicionuserPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'triviaApp.expedicionuser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'expedicionuser/:id/edit',
        component: ExpedicionuserPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'triviaApp.expedicionuser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'expedicionuser/:id/delete',
        component: ExpedicionuserDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'triviaApp.expedicionuser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
