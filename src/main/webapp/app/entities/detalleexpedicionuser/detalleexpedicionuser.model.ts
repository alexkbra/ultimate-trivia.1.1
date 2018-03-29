import { BaseEntity } from './../../shared';

export class Detalleexpedicionuser implements BaseEntity {
    constructor(
        public id?: number,
        public fechaRespueta?: any,
        public respuesta?: boolean,
        public preguntaId?: number,
        public expedicionuserId?: number,
    ) {
        this.respuesta = false;
    }
}
