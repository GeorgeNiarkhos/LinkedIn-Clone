import { Component } from '@angular/core';
import { LoginService } from './services/login.service';
import { SessionStorageService } from 'ngx-webstorage';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  constructor(private service:LoginService , private lSession:SessionStorageService ) {

  }
  
  getLogin(){
    return this.service.isLogedIn();
  }
  
}
