import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Chat } from '../chat';
import { Message } from '../message';
import { SessionStorageService} from 'ngx-webstorage';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient, private lSession:SessionStorageService) { }

  getChats() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.put<Chat[]>('https://localhost:8443/getChats', this.lSession.retrieve('email'), httpOptions);

  }

  getMessages( chatId : number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.put<Message[]>('https://localhost:8443/getMessages', chatId, httpOptions);
  }

  sendMessage( message : Message ) : Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.post("https://localhost:8443/sendMessage", message, httpOptions);

  }

  createChat( chat : Chat ) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.post("https://localhost:8443/createChat", chat, httpOptions);
    
  }
  
}
