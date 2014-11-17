package com.russell.test.redis.web.form;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;

import com.russell.test.redis.app.User;
import com.russell.test.redis.web.validator.DomainLogic;
import com.russell.test.redis.web.validator.UniqueEmail;

public class UserSignUp implements User {
    
    @Email
    @UniqueEmail(groups={DomainLogic.class})
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
	
	
	@Override
	public String toString() {
        return new ToStringBuilder(this).append("email", email).append("username", username).append("email", password)
                                        .append("email", password).toString();
	}
}
