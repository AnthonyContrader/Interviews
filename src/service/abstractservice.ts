import { Service } from './service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

/**
 * Service astratto, implementa tutti i metodi CRUD inviando request al server di SpringBoot. 
 * @param port il port del backend
 * @param type la mappatura del controller di ciascuna entità.
 * 
 * @see Service
 * 
 * @author Vittorio Valent
 */
export abstract class AbstractService<DTO> implements Service<DTO> {

    type: string;
    port: string = '8080';
    heads = {'Authorization': 'Bearer ' + localStorage.getItem('id_token')};

    constructor(protected http: HttpClient) {
    }

    getAll(): Observable<DTO[]> {
        return this.http.get<DTO[]>('http://localhost:' + this.port + '/' + this.type, {headers: this.heads});
    }

    read(id: any): Observable<DTO> {
        return this.http.get<DTO>('http://localhost:' + this.port + '/' + this.type + '/' + id, {headers: this.heads});
    }

    delete(id: any): Observable<any> {
        return this.http.delete('http://localhost:' + this.port + '/' + this.type + '/' + id, {headers: this.heads});
    }

    insert(dto: DTO): Observable<any> {
        return this.http.post('http://localhost:' + this.port + '/' + this.type, dto, {headers: this.heads});
    }

    update(dto: DTO): Observable<DTO> {
        return this.http.put<DTO>('http://localhost:' + this.port + '/' + this.type, dto, {headers: this.heads});

    }

}