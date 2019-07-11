import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private http: HttpClient) { }

  getUsers(): Observable<any> {
    return this.http.get('https://localhost:8443/getUsers');
  }

  getXml(/*users : Array<User>*/): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/xml',
        'Auth': 'admin:admin',
      }),
      responseType: 'blob' as 'blob',
    };
    return this.http.get('https://localhost:8443/getXml'/*+users*/, httpOptions);
  }

  getName(email : string) {
    return this.http.get('https://localhost:8443');
  }

}
