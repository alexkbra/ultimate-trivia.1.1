import { BaseEntity } from './../../shared';

export class Cuestionario implements BaseEntity {
    constructor(
        public id?: number,
        public nombre?: string,
        public nivels?: BaseEntity[],
        public expedicions?: BaseEntity[],
    ) {
    }
}
