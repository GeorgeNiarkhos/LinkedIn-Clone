package com.sdi1500103sdi1500109.Network;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;
import java.util.ArrayList;

@XmlRootElement(name = "xmlUser")
@XmlAccessorType(XmlAccessType.FIELD)
public class xmlUser {
	@XmlElement(name = "user")
	private User user;
	@XmlElement(name = "friends")
	private List<User> friends;
	@XmlElement(name = "posts")
	private List<Post> posts;
	@XmlElement(name = "postLikes")
	private List<PostLike> postLikes;
	@XmlElement(name = "jobs")
	private List<Job> jobs;
	@XmlElement(name = "comments")
	private List<Comment> comments;
	
	
	public xmlUser() {
		this.friends = new ArrayList<User>();
		this.posts = new ArrayList<Post>();
		this.postLikes = new ArrayList<PostLike>();
		this.jobs = new ArrayList<Job>();
		this.comments = new ArrayList<Comment>();
	}
	
	public void setxmlUser(User user) {
		this.user = user;
	}
	
	public User getxmlUser() {
		return this.user;
	}
	
	public void setxmlFriends(ArrayList<User> xFriends) {
		this.friends = xFriends;
	}
	
	public List<User> getxmlFriends() {
		return this.friends ;
	}
	
	public void setxmlPosts(ArrayList<Post> xPosts) {
		this.posts = xPosts;
	}
	
	public List<Post> getxmlPosts() {
		return this.posts ;
	}
	
	public void setxmlPostLikes(ArrayList<PostLike> xPostLikes) {
		this.postLikes = xPostLikes;
	}
	
	public List<PostLike> getxmlPostLikes() {
		return this.postLikes ;
	}
	
	public void setxmlJobs(ArrayList<Job> xJobs) {
		this.jobs = xJobs;
	}
	
	public List<Job> getxmlJobs() {
		return this.jobs ;
	}
	
	public void setxmlComments(ArrayList<Comment> xComments) {
		this.comments = xComments;
	}
	
	public List<Comment> getxmlComments() {
		return this.comments ;
	}
	
}
