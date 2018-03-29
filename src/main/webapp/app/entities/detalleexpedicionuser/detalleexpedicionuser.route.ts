import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { DetalleexpedicionuserComponent } from './detalleexpedicionuser.component';
import { DetalleexpedicionuserDetailComponent } from './detalleexpedicionuser-detail.component';
import { DetalleexpedicionuserPopupComponent } from './detalleexpedicionuser-dialog.component';
import { DetalleexpedicionuserDeletePopupComponent } from './detalleexpedicionuser-delete-dialog.component';

@Injectable()
export class DetalleexpedicionuserResolvePagingParams implements Resolve<any> {

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

export const detalleexpedicionuserRoute: Routes = [
    {
        path: 'detalleexpedicionuser',
        component: DetalleexpedicionuserComponent,
        resolve: {
            'pagingParams': DetalleexpedicionuserResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'triviaApp.detalleexpedicionuser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'detalleexpedicionuser/:id',
        component: DetalleexpedicionuserDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'triviaApp.detalleexpedicionuser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const detalleexpedicionuserPopupRoute: Routes = [
    {
        path: 'detalleexpedicionuser-new',
        component: DetalleexpedicionuserPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'triviaApp.detalleexpedicionuser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'detalleexpedicionuser/:id/edit',
        component: DetalleexpedicionuserPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'triviaApp.detalleexpedicionuser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'detalleexpedicionuser/:id/delete',
        component: DetalleexpedicionuserDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'triviaApp.detalleexpedicionuser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
