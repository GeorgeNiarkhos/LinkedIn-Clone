package com.sdi1500103sdi1500109.Network;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Relationship {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Integer id;
	private String userOneEmail ;
	private String userTwoEmail ;
	private String status;		//status:1->friends, status:2->pending, status:0->Blocked?
	private String actionUserEmail;
	
	
	
	

	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserOneEmail() {
		return userOneEmail;
	}
	public void setUserOneEmail(String userOneEmail) {
		this.userOneEmail = userOneEmail;
	}
	public String getUserTwoEmail() {
		return userTwoEmail;
	}
	public void setUserTwoEmail(String userTwoEmail) {
		this.userTwoEmail = userTwoEmail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getActionUserEmail() {
		return actionUserEmail;
	}
	public void setActionUserEmail(String actionUserEmail) {
		this.actionUserEmail = actionUserEmail;
	}
	
	
	

}
