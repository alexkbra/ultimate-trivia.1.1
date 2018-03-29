import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Detalleexpedicionuser } from './detalleexpedicionuser.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class DetalleexpedicionuserService {

    private resourceUrl = SERVER_API_URL + 'api/detalleexpedicionusers';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(detalleexpedicionuser: Detalleexpedicionuser): Observable<Detalleexpedicionuser> {
        const copy = this.convert(detalleexpedicionuser);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(detalleexpedicionuser: Detalleexpedicionuser): Observable<Detalleexpedicionuser> {
        const copy = this.convert(detalleexpedicionuser);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<Detalleexpedicionuser> {
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
        entity.fechaRespueta = this.dateUtils
            .convertDateTimeFromServer(entity.fechaRespueta);
    }

    private convert(detalleexpedicionuser: Detalleexpedicionuser): Detalleexpedicionuser {
        const copy: Detalleexpedicionuser = Object.assign({}, detalleexpedicionuser);

        copy.fechaRespueta = this.dateUtils.toDate(detalleexpedicionuser.fechaRespueta);
        return copy;
    }
}
