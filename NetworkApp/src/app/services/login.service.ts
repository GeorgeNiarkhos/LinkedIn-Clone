import { Injectable } from '@angular/core';
import { User } from '../user';
import { HttpClient, HttpClientModule, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { SessionStorageService } from 'ngx-webstorage';



@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private logedIn = false;
  private isAdmin = false;

  constructor(private http: HttpClient, private lSession:SessionStorageService) { }

  isLogedIn() : boolean{
    return ( this.lSession.retrieve('email') != null  );
  }

  getAdmin() : boolean {
    return ( this.lSession.retrieve('email') == "admin" );
  }

  setAdmin(value : boolean ) {
    this.isAdmin = value;
  }

  setLogedIn(value : boolean ) {
    this.logedIn = value;
  }

  loginUser(user : User) : Observable<any>{
    return this.http.put("https://localhost:8443/loginUser", user);
  }

  getEmail() {
    return this.lSession.retrieve('email');
  }
  
}
