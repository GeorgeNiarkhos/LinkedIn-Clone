import { Injectable } from '@angular/core';
import { SessionStorageService } from 'ngx-webstorage';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Notification } from '../notification';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor( private http : HttpClient , private session : SessionStorageService ) { }

  getNotifications() : Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.session.retrieve('email')+':'+this.session.retrieve('password'),
      }),
    };
    return this.http.get("https://localhost:8443/getNotifications/" + this.session.retrieve('email') , httpOptions);
  }

  newNotification( notification : Notification )  { 
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.session.retrieve('email')+':'+this.session.retrieve('password'),
      }),
    };
    return this.http.post("https://localhost:8443/newNotification", notification, httpOptions);
  }


}
