import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TriviaSharedModule } from '../../shared';
import { TriviaAdminModule } from '../../admin/admin.module';
import {
    ExpedicionuserService,
    ExpedicionuserPopupService,
    ExpedicionuserComponent,
    ExpedicionuserDetailComponent,
    ExpedicionuserDialogComponent,
    ExpedicionuserPopupComponent,
    ExpedicionuserDeletePopupComponent,
    ExpedicionuserDeleteDialogComponent,
    expedicionuserRoute,
    expedicionuserPopupRoute,
    ExpedicionuserResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...expedicionuserRoute,
    ...expedicionuserPopupRoute,
];

@NgModule({
    imports: [
        TriviaSharedModule,
        TriviaAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ExpedicionuserComponent,
        ExpedicionuserDetailComponent,
        ExpedicionuserDialogComponent,
        ExpedicionuserDeleteDialogComponent,
        ExpedicionuserPopupComponent,
        ExpedicionuserDeletePopupComponent,
    ],
    entryComponents: [
        ExpedicionuserComponent,
        ExpedicionuserDialogComponent,
        ExpedicionuserPopupComponent,
        ExpedicionuserDeleteDialogComponent,
        ExpedicionuserDeletePopupComponent,
    ],
    providers: [
        ExpedicionuserService,
        ExpedicionuserPopupService,
        ExpedicionuserResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TriviaExpedicionuserModule {}
