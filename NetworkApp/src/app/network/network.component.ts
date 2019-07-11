import { Component, OnInit } from '@angular/core';
import { NetworkService } from '../services/network.service';
import { SessionStorageService } from 'ngx-webstorage';

@Component({
  selector: 'app-network',
  templateUrl: './network.component.html',
  styleUrls: ['./network.component.css']
})
export class NetworkComponent implements OnInit {

  users : Array<any>;
  searchText : string;

  constructor( private networkService: NetworkService, private session:SessionStorageService ) { }

  ngOnInit() {
    //TODO : CHECK IF STORAGE IS AVAILIABLE AT ALL TIMES 
    this.networkService.getFriends(this.session.retrieve('email')).subscribe( data => {
      this.users = data;
    });
  }

  searchNetwork() {
    this.networkService.getUsersByKeyword(this.searchText).subscribe(
      data => {
        this.users = data;
      }); 
  }

}
