package com.sdi1500103sdi1500109.Network;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "User")
@XmlAccessorType (XmlAccessType.NONE)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@XmlAttribute
	private String skills;
	@XmlAttribute
	private String eduExperience;
	@XmlAttribute
	private String workExperience;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String phone;
	@XmlAttribute
	private String email;
	private String pass;
	private String permissions;
	private long score;
	private String filePath;
		
	public void printUser() {
		System.out.println(this.getName());
		System.out.println(this.getEmail());
		System.out.println(this.getPass());
		System.out.println(this.getPhone());
	}
	

	public String getName() {
		return name; 
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId( ) {
		return id;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
	
	public String getEduExperience() {
		return eduExperience;
	}

	public void setEduExperience(String eduExperience) {
		this.eduExperience = eduExperience;
	}
	
	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}
	
	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}


	public long getScore() {
		return score;
	}


	public void setScore(long score) {
		this.score = score;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	
	
	
	
}
