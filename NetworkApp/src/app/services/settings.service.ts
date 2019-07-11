import { Injectable } from '@angular/core';
import { User } from '../user';
import { HttpClient, HttpClientModule, HttpParams, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { SessionStorageService } from 'ngx-webstorage'

@Injectable({
  providedIn: 'root'
})

export class SettingsService {
 
  constructor(private http: HttpClient , private lSession:SessionStorageService ) { }

  submitSettings(user : User) : Observable<any>{
    // Send a JSON with two users.
    // First user has the previous settings
    // Second user has the new settings
    const body : User[] = [{
      photo: null,
      name: null,
      email: this.lSession.retrieve('email'),
      pass:  this.lSession.retrieve('password'),
      phone: null,
      workExperience: null,
      skills: null,
      eduExperience: null,
      img : null,
      permissions : null, 
      filePath : null,
    }, {
      photo: null,
      name: null,
      email: user.email,
      pass: user.pass,
      phone: null,
      workExperience: null,
      skills: null,
      eduExperience: null,
      img : null,
      permissions : null,
      filePath : null,

    }];
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.put("https://localhost:8443/submitSettings", body, httpOptions);
  }
  
}