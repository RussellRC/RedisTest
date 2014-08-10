package com.russell.test.redis.web;

import org.hibernate.validator.constraints.Email;

import com.russell.test.redis.app.User;

public class UserSignUp implements User {
    
    @Email
	private String email;  
	private String username;
	private String password;
	private String password2;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
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
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	
	
}
