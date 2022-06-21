package com.anil.example.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {

	@Id
	private String username;
	
	private String password;
	
	private String phonenum;
	
	public String getUsername() {
		return username;
	}
	@Override
	public String toString() {
		return "Admin [username=" + username + ", password=" + password + ", phonenum=" + phonenum + "]";
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
}
