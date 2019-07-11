import { Component, OnInit } from '@angular/core';
import { Relationship } from '../relationship';
import { NetworkService } from '../services/network.service';
import { SessionStorageService } from 'ngx-webstorage';
import { NotificationService } from '../services/notification.service';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit {

  relationships : Relationship[];
  notifications : Notification[];

  constructor(private service : NotificationService, private networkService: NetworkService, private session:SessionStorageService) { }

  ngOnInit() {
    this.networkService.getUserFRequests(this.session.retrieve('email')).subscribe(
      data => {
        this.relationships = data;
      });
    this.getNotifications();
  }

  getNotifications() {
    this.service.getNotifications().subscribe( data => 
      {
        this.notifications = data;
      });
  }

  acceptRequest( relationship : Relationship ) {
    this.networkService.acceptRequest( relationship.id ).subscribe();
  }

  declineRequest( relationship : Relationship ) {
    this.networkService.declineRequest( relationship.id ).subscribe();
  }



}
