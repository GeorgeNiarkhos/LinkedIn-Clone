package com.sdi1500103sdi1500109.Network;

public class FetchChat {
	
	private Integer id;
	private String user;
	
	
	public FetchChat(Integer id, String user) {
		super();
		this.id = id;
		this.user = user;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
	
}
