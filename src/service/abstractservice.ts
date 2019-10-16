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

    constructor(protected http: HttpClient) {
    }

    getAll(): Observable<DTO[]> {
        let heads = {'Authorization': 'Bearer ' + localStorage.getItem('id_token')};
        return this.http.get<DTO[]>('http://localhost:' + this.port + '/' + this.type, {headers: heads});
    }

    read(id: number): Observable<DTO> {
        let heads = {'Authorization': 'Bearer ' + localStorage.getItem('id_token')};
        return this.http.get<DTO>('http://localhost:' + this.port + '/' + this.type + '/' + id, {headers: heads});
    }

    delete(id: number): Observable<any> {
        let heads = {'Authorization': 'Bearer ' + localStorage.getItem('id_token')};
        return this.http.delete('http://localhost:' + this.port + '/' + this.type + '/' + id, {headers: heads});
    }

    insert(dto: DTO): Observable<any> {
        let heads = {'Authorization': 'Bearer ' + localStorage.getItem('id_token')};
        return this.http.post('http://localhost:' + this.port + '/' + this.type, dto, {headers: heads});
    }

    update(dto: DTO): Observable<DTO> {
        let heads = {'Authorization': 'Bearer ' + localStorage.getItem('id_token')};
        return this.http.put<DTO>('http://localhost:' + this.port + '/' + this.type, dto, {headers: heads});

    }

}