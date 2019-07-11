import { Component, OnInit } from '@angular/core';
import {NgForm, FormsModule} from '@angular/forms';
import { User } from '../user';
import { LoginService } from '../services/login.service';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { Router } from '@angular/router'
import { SessionStorageService } from 'ngx-webstorage';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user : User;
  validInfo : boolean;


  constructor(private service : LoginService , private router : Router, private lSession : SessionStorageService) { }

  ngOnInit() {
    this.validInfo = true;
  }

  onLogin(form : NgForm){
    this.user = new User();
    const values = form.value;
    this.user.email = values.email;
    this.user.pass = values.password;
    this.service.loginUser(this.user).subscribe( user => {
      if ( user == null ) {
        this.service.setLogedIn(false);
        this.validInfo = false;
      } else {
        if(user.email == "admin" && user.pass == "admin"){
          this.lSession.store('email', user.email);
          this.lSession.store('password', user.pass);
          this.router.navigate(['admin']);   
        }
        else{
          this.service.setLogedIn(true);
          this.lSession.store('name', user.name);
          this.lSession.store('email', user.email);
          this.lSession.store('password', user.pass);
          this.router.navigate(['home']);
        }
      
      }
      
    });
  }

}
