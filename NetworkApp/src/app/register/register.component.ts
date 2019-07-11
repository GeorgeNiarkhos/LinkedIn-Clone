import { Component, OnInit, ViewChild } from '@angular/core';
import {NgForm, FormsModule} from '@angular/forms';
import { RegisterService } from '../services/register.service';


import { User } from '../user'
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user : User;
  validInfo : boolean;

  constructor(private serv : RegisterService, private router : Router  ) { }

  ngOnInit() {
    this.validInfo = true;
  }

  onRegister( form: NgForm ) {

    const values = form.value;
    console.log(values.email);
    this.user = new User();
    this.user.name = values.name;
    this.user.email = values.email;

    this.user.pass = values.password;
    if ( this.user.pass != values.password2 ) {
      form.reset();
      this.validInfo = false;
      return null;
    }

    this.serv.registerUser(this.user).subscribe( user => {
      if ( user == null ) {
        form.reset();
        this.validInfo = false;
      } else {
        this.router.navigate(['']);
      }
    });
  }
}
