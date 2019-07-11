import { Component, OnInit } from '@angular/core';
import {NgForm, FormsModule} from '@angular/forms';
import { User } from '../user'
import { LoginService } from '../services/login.service';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { Router } from '@angular/router'
import { SettingsService } from '../services/settings.service';
import { SessionStorageService } from 'ngx-webstorage';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  message : string = ""
  user : User;

  constructor(private service : SettingsService , private router : Router , private lSession:SessionStorageService) { }

  ngOnInit() {
    // Initialize user object
    this.user = new User();
    this.user.email = this.lSession.retrieve('email');
    this.user.pass = this.lSession.retrieve('password');
  }
  
  onSubmit(form : NgForm){
    
    // Probably add validation here
    this.service.submitSettings(this.user).subscribe( user => {
      if ( user == null ) {
        this.message = "Error"
      } else {
        this.message = "Changes saved!"
        this.lSession.store('email', this.user.email);
        this.lSession.store('password', this.user.pass);
      }
    });
  }

}
