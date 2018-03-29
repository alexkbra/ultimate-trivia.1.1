import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TriviaSharedModule } from '../../shared';
import {
    DetalleexpedicionuserService,
    DetalleexpedicionuserPopupService,
    DetalleexpedicionuserComponent,
    DetalleexpedicionuserDetailComponent,
    DetalleexpedicionuserDialogComponent,
    DetalleexpedicionuserPopupComponent,
    DetalleexpedicionuserDeletePopupComponent,
    DetalleexpedicionuserDeleteDialogComponent,
    detalleexpedicionuserRoute,
    detalleexpedicionuserPopupRoute,
    DetalleexpedicionuserResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...detalleexpedicionuserRoute,
    ...detalleexpedicionuserPopupRoute,
];

@NgModule({
    imports: [
        TriviaSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        DetalleexpedicionuserComponent,
        DetalleexpedicionuserDetailComponent,
        DetalleexpedicionuserDialogComponent,
        DetalleexpedicionuserDeleteDialogComponent,
        DetalleexpedicionuserPopupComponent,
        DetalleexpedicionuserDeletePopupComponent,
    ],
    entryComponents: [
        DetalleexpedicionuserComponent,
        DetalleexpedicionuserDialogComponent,
        DetalleexpedicionuserPopupComponent,
        DetalleexpedicionuserDeleteDialogComponent,
        DetalleexpedicionuserDeletePopupComponent,
    ],
    providers: [
        DetalleexpedicionuserService,
        DetalleexpedicionuserPopupService,
        DetalleexpedicionuserResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TriviaDetalleexpedicionuserModule {}
