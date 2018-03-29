import { BaseEntity } from './../../shared';

export class Empresas implements BaseEntity {
    constructor(
        public id?: number,
        public razonSocial?: string,
        public nit?: number,
        public digitoVerificacion?: number,
        public telefono?: number,
        public telefonoContacto?: number,
        public email?: string,
        public direccion?: string,
        public paginaWeb?: string,
        public fechaRegistro?: any,
        public publicidads?: BaseEntity[],
    ) {
    }
}
