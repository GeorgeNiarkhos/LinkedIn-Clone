import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { ProfileService } from '../services/profile.service';
import { User } from '../user'
import { Chat } from '../chat';
import { SessionStorageService } from 'ngx-webstorage';
import { MessageService } from '../services/message.service';
import { NetworkService } from '../services/network.service';
import { Relationship } from '../relationship';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user : User;
  preview : boolean = true;
  myProfile : boolean = false;
  check0 : boolean;
  check1 : boolean;
  check2 : boolean;
  check3 : boolean;
  check4 : boolean;
  file = null;


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private profileService: ProfileService,
    private location: Location,
    private session : SessionStorageService,
    private service : MessageService,
    private networkService : NetworkService
  ) {}

  ngOnInit() {
    this.file = null;
    this.getUser();
    
  }

  getChecks() {

    console.log(this.user.permissions);
    if ( this.user.permissions == null ) {
      this.check0 = false;
      this.check1 = false;
      this.check2 = false;
      this.check3 = false;
      this.check4 = false;
    } else {
      this.check0 = this.user.permissions[0] == '1';
      this.check1 = this.user.permissions[1] == '1';
      this.check2 = this.user.permissions[2] == '1';
      this.check3 = this.user.permissions[3] == '1';
      this.check4 = this.user.permissions[4] == '1';
    }
  }

  checkPreview() {
    if ( this.user.email == this.session.retrieve('email') ) {
      this.myProfile = true;
    }
  }

  getUser() {
    // get the email from the url 
    this.user = new User();
    const emailParam = this.route.snapshot.paramMap.get('email');
    this.profileService.getUser(emailParam).subscribe(
      data => {
        this.user = data;
        this.checkPreview();
        this.getChecks();
      });
  }

  toggle() {
    this.preview = !this.preview;
  }

  save() {
    this.user.permissions = "";
    if ( this.check0 )  { 
      this.user.permissions += "1";
    } else { this.user.permissions += "0"; }
    if ( this.check1 )  { 
      this.user.permissions += "1";
    } else { this.user.permissions += "0"; }
    if ( this.check2 )  { 
      this.user.permissions += "1";
    } else { this.user.permissions += "0"; }
    if ( this.check3 )  { 
      this.user.permissions += "1";
    } else { this.user.permissions += "0"; }
    if ( this.check4 )  { 
      this.user.permissions += "1";
    } else { this.user.permissions += "0"; }

    this.profileService.saveUser(this.user).subscribe(
      data => {
        this.user = data;
        var fd = new FormData();
        console.log("file name ="+this.file.name);
        fd.append('file', this.file, this.file.name);
        this.profileService.saveImg(fd).subscribe();
      });
    this.toggle();
  }

  createChat() {
    var chat = new Chat();
    chat.user1 = this.session.retrieve('email');
    chat.user2= this.user.email;
    this.service.createChat(chat).subscribe( chat => {
      this.router.navigate(['messages']);
    });
  }

  addFriend() {
    var rel = new Relationship();
    rel.actionUserEmail = this.session.retrieve('email');
    rel.userOneEmail = this.user.email;
    this.networkService.addFriend(rel).subscribe();
  }

  selectFile(event) {
    console.log(event);
    this.file = event.target.files[0];
  } 

}
