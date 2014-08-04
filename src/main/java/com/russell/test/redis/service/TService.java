package com.russell.test.redis.service;

import com.russell.test.redis.bo.ServiceUser;


public interface TService {

    void save(ServiceUser user);
    
    ServiceUser findById(String id);

    ServiceUser findByEmail(String email);
}
