import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Expedicionuser } from './expedicionuser.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ExpedicionuserService {

    private resourceUrl = SERVER_API_URL + 'api/expedicionusers';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(expedicionuser: Expedicionuser): Observable<Expedicionuser> {
        const copy = this.convert(expedicionuser);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(expedicionuser: Expedicionuser): Observable<Expedicionuser> {
        const copy = this.convert(expedicionuser);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<Expedicionuser> {
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

    private convert(expedicionuser: Expedicionuser): Expedicionuser {
        const copy: Expedicionuser = Object.assign({}, expedicionuser);
        copy.fechaRegistro = this.dateUtils
            .convertLocalDateToServer(expedicionuser.fechaRegistro);
        return copy;
    }
}
