import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRecruiter } from 'app/shared/model/interviews1/recruiter.model';

type EntityResponseType = HttpResponse<IRecruiter>;
type EntityArrayResponseType = HttpResponse<IRecruiter[]>;

@Injectable({ providedIn: 'root' })
export class RecruiterService {
    private resourceUrl = SERVER_API_URL + 'interviews1/api/recruiters';

    constructor(private http: HttpClient) {}

    create(recruiter: IRecruiter): Observable<EntityResponseType> {
        return this.http.post<IRecruiter>(this.resourceUrl, recruiter, { observe: 'response' });
    }

    update(recruiter: IRecruiter): Observable<EntityResponseType> {
        return this.http.put<IRecruiter>(this.resourceUrl, recruiter, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRecruiter>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IRecruiter[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
