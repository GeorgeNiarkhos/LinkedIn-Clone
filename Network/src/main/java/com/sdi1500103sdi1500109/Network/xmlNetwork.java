package com.sdi1500103sdi1500109.Network;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "xmlNetwork")
@XmlAccessorType(XmlAccessType.FIELD)
public class xmlNetwork {
	@XmlElement(name = "users")
	private List<xmlUser> users;
	
	public xmlNetwork() {
		this.users = new ArrayList<xmlUser>();
	}
	
	public void addXmlUser(xmlUser user) {
		this.users.add(user);
	}
	
	public List<xmlUser> getUsers(){
		return this.users;
	}
	
	public void setUsers(ArrayList<xmlUser> usersx) {
		this.users = usersx;
	}
}
