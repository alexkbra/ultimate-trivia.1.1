import { BaseEntity } from './../../shared';

export const enum TipoPregunta {
    'ABIERTAS',
    'UNICARESPUESTA',
    'SELECCIONMULTIPLE'
}

export class Pregunta implements BaseEntity {
    constructor(
        public id?: number,
        public cortaDescripcion?: string,
        public descripcion?: string,
        public pista?: string,
        public tipoPregunta?: TipoPregunta,
        public respuestas?: BaseEntity[],
        public detalleexpedicionusers?: BaseEntity[],
        public nivels?: BaseEntity[],
    ) {
    }
}
