import { BaseEntity } from './../../shared';

export const enum TipoArchivo {
    'VIDEO',
    'MUSIC',
    'IMAGE'
}

export class Galerias implements BaseEntity {
    constructor(
        public id?: number,
        public uri?: string,
        public tipoArchivo?: TipoArchivo,
        public publicidads?: BaseEntity[],
    ) {
    }
}
