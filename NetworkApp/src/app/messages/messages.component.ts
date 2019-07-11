import { Component, OnInit } from '@angular/core';
import { Chat } from '../chat';
import { Message} from '../message';
import { MessageService } from '../services/message.service';
import { NgForm } from '@angular/forms';
import { SessionStorageService } from 'ngx-webstorage';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  //  array of user covnersations
  chats : Chat[]; 
  selectedChat : number;
  // array of user messages in a conversation
  messages : Message[];
  atLeastOne: boolean;
  // Array of chat names


  constructor( private messageService : MessageService, private session : SessionStorageService) { }

  ngOnInit() {
    this.atLeastOne = false;
    this.messageService.getChats().subscribe(
      data => {
        this.chats = data;
        if ( this.chats.length > 0 ) {
          this.atLeastOne = true;
          this.selectChat(this.chats[0].id);
        }
      });
  }

  getChats() {
    this.messageService.getChats().subscribe(
      data => {
        this.chats = data;
        if ( this.chats.length > 0 ) {
          this.atLeastOne = true;
        }
      });
  }

  sendMessage( form : NgForm ) {
    var message = new Message();
    message.chatId = this.selectedChat;
    message.content = form.value.message;
    form.reset();
    message.sender = this.session.retrieve('name');
    this.messageService.sendMessage(message).subscribe( msg => {
      this.messages.push(msg);
    } );
  }

  selectChat( id : number ) {
    this.selectedChat = id;
    this.messageService.getMessages(this.selectedChat).subscribe(
      data => {
        console.log(data);
        this.messages = data;
      });
  }

}
