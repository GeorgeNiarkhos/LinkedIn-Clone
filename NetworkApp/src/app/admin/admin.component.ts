import { Component, OnInit } from '@angular/core';
import { UsersService } from '../services/users.service';
import { User } from '../user';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  users : Array<User>;
  selectedUsers : Array<User>;

  constructor( private usersService : UsersService) { }

  ngOnInit() {
    this.usersService.getUsers().subscribe( data => {
      this.users = data;
    });
    // this.usersService.getXml().subscribe();
  }

  downloadXml(){
    this.usersService.getXml(/*this.selectedUsers*/).subscribe(
      (res) => {
        var url = window.URL.createObjectURL(res);
        var element = document.createElement('a');
        document.body.appendChild(element);
        element.setAttribute('style', 'display: none');
        element.href = url;
        element.download = "file.xml"
        element.click();
        window.URL.revokeObjectURL(url);
        element.remove();  
      }
    );
  }

}
