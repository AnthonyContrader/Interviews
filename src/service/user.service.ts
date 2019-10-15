import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { UserDTO } from 'src/dto/userdto';
import { HttpClient } from '@angular/common/http';
import { LoginDTO } from 'src/dto/logindto';
import { Observable } from 'rxjs';

/**
 * I service sono decorati da @Injectable. 
 * Qui trovate, oltre ai metodi ereditati dall'Abstract,
 *  il metodo per il login (in mirror con il backend).
 * 
 * @author Vittorio Valent
 * 
 * @see AbstractService
 */
@Injectable({
  providedIn: 'root'
})
export class UserService extends AbstractService<UserDTO> {

  constructor(http: HttpClient) {
    super(http);
    this.type = 'api/users';
  }

  login(loginDTO: LoginDTO): Observable<any> {
    return this.http.post<any>('http://localhost:8080/api/authenticate', loginDTO);
  }

  getAccount(): Observable<UserDTO> {
    return this.http.get<any>('http://localhost:8080/api/account', {headers: this.heads});
  }

  read(login: string): Observable<UserDTO> {
    return this.http.get<UserDTO>('http://localhost:' + this.port + '/' + this.type + '/' + login, {headers: this.heads});
  }

  delete(login: string): Observable<any> {
    return this.http.delete('http://localhost:' + this.port + '/' + this.type + '/' + login, {headers: this.heads});
  }

  insert(dto: UserDTO): Observable<any> {
    return this.http.post('http://localhost:' + this.port + '/api/register', dto, {headers: this.heads});
  }

}
