import { BaseEntity } from './../../shared';

export class Expedicion implements BaseEntity {
    constructor(
        public id?: number,
        public nombre?: string,
        public expedicionusers?: BaseEntity[],
        public cuestionarios?: BaseEntity[],
    ) {
    }
}
