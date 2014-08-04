package com.russell.test.redis.bo;

import com.russell.test.redis.app.User;


public interface ServiceUser extends User {
    
	long getId();
    void setId(long id);
}
