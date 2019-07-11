import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  registerUser( user : User) : Observable<any>{    
    return this.http.post("https://localhost:8443/registerUser", user);
  }

}
