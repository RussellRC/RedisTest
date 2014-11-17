package com.russell.test.redis.bo;

import java.io.Serializable;

import com.russell.test.redis.app.User;


public class ServiceUserImpl implements ServiceUser, Serializable {
    
    /** {@link java.io.Serializable} */
    private static final long serialVersionUID = -610067016430934037L;
    private long id;
    private String email;
    private String username;
    private String password;
    
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    @Override
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public static ServiceUser newServiceUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user can not be null");
        }
        
        final ServiceUserImpl su = new ServiceUserImpl();
        su.email = user.getEmail();
        su.username = user.getUsername();
        su.password = user.getPassword();
        return su;
    }
}
