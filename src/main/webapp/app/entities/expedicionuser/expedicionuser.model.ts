import { BaseEntity } from './../../shared';

export class Expedicionuser implements BaseEntity {
    constructor(
        public id?: number,
        public nickname?: string,
        public fechaRegistro?: any,
        public detalleexpedicionusers?: BaseEntity[],
        public useridId?: number,
        public expedicionId?: number,
    ) {
    }
}
