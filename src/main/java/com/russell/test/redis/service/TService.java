package com.russell.test.redis.service;

import com.russell.test.redis.bo.ServiceUser;
import com.russell.test.redis.bo.User;


public interface TService {

    void save(ServiceUser user);
    
    User findById(String id);

    User findByEmail(String email);
}
