import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule, HttpParams, HttpHeaders} from '@angular/common/http';
import { Post } from '../post';
import { Observable } from 'rxjs';
import { Like } from '../like';
import { PostComment } from '../comment';
import { SessionStorageService } from 'ngx-webstorage';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor( private http : HttpClient , private lSession:SessionStorageService) { }

  getPosts( email : string ) : Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.get("https://localhost:8443/getPosts/"+email, httpOptions);
  }
  

  newPost( post : Post ) : Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.post("https://localhost:8443/newPost", post, httpOptions);
  }

  likePost( like : Like ) : Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.post("https://localhost:8443/like", like, httpOptions);
  }

  getLikes ( post : Post ) : Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.get("https://localhost:8443/getLikes/"+post.id, httpOptions);
  }

  commentPost( comment : PostComment ) : Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.post("https://localhost:8443/comment", comment, httpOptions);
  }

  getComments( post : Post ) : Observable<any>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Auth': this.lSession.retrieve('email')+':'+this.lSession.retrieve('password'),
      }),
    };
    return this.http.get("https://localhost:8443/getComments/" + post.id, httpOptions);
  }


}
