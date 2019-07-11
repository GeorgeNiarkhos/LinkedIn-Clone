import { Component, OnInit } from '@angular/core';
import { User } from '../user'
import { SessionStorageService} from 'ngx-webstorage';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  profileAdress : string;

  constructor(private lSession:SessionStorageService , private service : LoginService ) { }

  ngOnInit() {
    // this.service.getEmail().subscribe(
    //   data => {
    //     this.profileAdress = data;
    //   }
    // );
    this.profileAdress = "/profile/"+this.lSession.retrieve('email');
    // this.profileAdress = this.lSession.retrieve('email');
  }

}
