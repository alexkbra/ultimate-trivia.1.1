import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Empresas } from './empresas.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class EmpresasService {

    private resourceUrl = SERVER_API_URL + 'api/empresas';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(empresas: Empresas): Observable<Empresas> {
        const copy = this.convert(empresas);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(empresas: Empresas): Observable<Empresas> {
        const copy = this.convert(empresas);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<Empresas> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.fechaRegistro = this.dateUtils
            .convertLocalDateFromServer(entity.fechaRegistro);
    }

    private convert(empresas: Empresas): Empresas {
        const copy: Empresas = Object.assign({}, empresas);
        copy.fechaRegistro = this.dateUtils
            .convertLocalDateToServer(empresas.fechaRegistro);
        return copy;
    }
}
