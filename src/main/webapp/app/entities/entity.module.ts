import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { TriviaEmpresasModule } from './empresas/empresas.module';
import { TriviaGaleriasModule } from './galerias/galerias.module';
import { TriviaPublicidadModule } from './publicidad/publicidad.module';
import { TriviaExpedicionModule } from './expedicion/expedicion.module';
import { TriviaExpedicionuserModule } from './expedicionuser/expedicionuser.module';
import { TriviaCuestionarioModule } from './cuestionario/cuestionario.module';
import { TriviaNivelModule } from './nivel/nivel.module';
import { TriviaPreguntaModule } from './pregunta/pregunta.module';
import { TriviaRespuestaModule } from './respuesta/respuesta.module';
import { TriviaDetalleexpedicionuserModule } from './detalleexpedicionuser/detalleexpedicionuser.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        TriviaEmpresasModule,
        TriviaGaleriasModule,
        TriviaPublicidadModule,
        TriviaExpedicionModule,
        TriviaExpedicionuserModule,
        TriviaCuestionarioModule,
        TriviaNivelModule,
        TriviaPreguntaModule,
        TriviaRespuestaModule,
        TriviaDetalleexpedicionuserModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TriviaEntityModule {}
