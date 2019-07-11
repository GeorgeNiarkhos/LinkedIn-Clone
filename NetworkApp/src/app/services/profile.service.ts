import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../user'
import { HttpClient, HttpClientModule, HttpParams, HttpHeaders} from '@angular/common/http';
import { SessionStorageService } from 'ngx-webstorage';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient, private lSession:SessionStorageService) { }
  // Send http request on backend including the user
  // email on body. Backend will return all user info in JSON
  getUser(email: string): Observable<any> {
    const body: User = {
      photo: null,
      name: null,
      email: email,
      pass: null,
      phone: null,
      workExperience: null,
      skills: null,
      eduExperience: null,
      img : null,
      permissions : null,
      filePath  : null,
    }
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.put("https://localhost:8443/profile", body, httpOptions);
  }

  saveUser(user: User): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.put("https://localhost:8443/saveUser", user, httpOptions);
  }

  saveImg(fd): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.put("https://localhost:8443/saveImg", fd, httpOptions);  
  }


}
