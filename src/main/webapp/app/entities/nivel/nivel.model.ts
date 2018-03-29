import { BaseEntity } from './../../shared';

export class Nivel implements BaseEntity {
    constructor(
        public id?: number,
        public nombre?: string,
        public fechaInicio?: any,
        public fechaFinal?: any,
        public publicidads?: BaseEntity[],
        public preguntas?: BaseEntity[],
        public cuestionarios?: BaseEntity[],
    ) {
    }
}
