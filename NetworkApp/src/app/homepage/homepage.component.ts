import { Component, OnInit } from '@angular/core';
import { User } from '../user'
import { ProfileService } from '../services/profile.service';
import { SessionStorageService } from 'ngx-webstorage';
import { Post } from '../post';
import { NgForm } from '@angular/forms';
import { HomeService } from '../services/home.service';
import { PostComment } from '../comment';
import { Like } from '../like';
import { NotificationService } from '../services/notification.service';
import { Notification } from '../notification';
import { NetworkService } from '../services/network.service';
import { Relationship } from '../relationship';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  user : User;
  posts : Post[];
  comments : Comment[];
  likes : Like[];
  moreInfo : number;
  friends : number;
  relat : Relationship[];

  constructor( private service : HomeService, private service2 : ProfileService , private lsession: SessionStorageService , private notificationService : NotificationService 
    , private networkService : NetworkService, userService : UsersService) { }


  ngOnInit() {
    
    this.friends = 0;
    this.service2.getUser(this.lsession.retrieve('email')).subscribe(
      data => {
        this.user = data;
        this.getFriends();
      }
    );

    this.service.getPosts(this.lsession.retrieve('email')).subscribe(
      data => {
        this.posts = data;
        for ( var i = 0; i <this.posts.length; i++ ) {
          this.getLikes(this.posts[i]);
        }
      }
    );

  }

  getFriends() {
    this.networkService.getFriends( this.user.email).subscribe( friend => {
      this.relat = friend;
      if ( this.relat != null ) {
        this.friends = this.relat.length;
      }
    })
  }

  newPost( form : NgForm ) {
    var post = new Post();
    post.content = form.value.content;
    post.email = this.user.email;

    this.service.newPost(post).subscribe(
      data => {
        form.reset();
        this.posts.unshift(data);
      }   
    );
  }

  likePost( post : Post ) {
    var like = new Like();
    like.email = this.lsession.retrieve('email');
    like.postId = post.id;
    this.service.likePost(like).subscribe( data => {
      if ( data != null ) {
        var notification = new Notification();
        notification.message = like.email + " liked your post: " + post.content.substring(0,30) + "..."
        notification.email = post.email;
        post.likes++;
        this.notificationService.newNotification( notification ).subscribe();
      } else {
        window.alert("You already like this post.");
      }
    });
  }

  getComments( post : Post ) {
    this.service.getComments(post).subscribe( data => {
      this.comments = data;
    })
  }


  commentPost ( post : Post , form : NgForm ) {
    var comment = new PostComment();
    comment.postId = post.id;
    comment.email = this.lsession.retrieve('email');
    comment.comment = form.value.comment;
    this.service.commentPost(comment).subscribe( data => {

      this.comments.unshift(data);
      form.reset();
      var notification = new Notification();
      notification.message = comment.email + " commented on your post: " + post.content.substring(0,30) + "..."
      notification.email = post.email;
      this.notificationService.newNotification( notification ).subscribe();

    });
  }

  showMore( post  : Post ) {
    this.moreInfo = post.id ;
    this.getComments( post );
    this.getLikes( post );
  }

  getLikes( post : Post ) {
    this.service.getLikes(post).subscribe( likes => {
      this.likes = likes;
      post.likes = this.likes.length;
    });
  }



}
