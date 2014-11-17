package com.russell.test.redis.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.russell.test.redis.service.TService;
import com.russell.test.redis.web.form.UserSignUp;

@Component
public class TFacade {
    private static final Logger logger = LoggerFactory.getLogger(TFacade.class);
    
    @Resource
    TService service;
    
    public void register(UserSignUp userSignUp) {
        logger.debug("Registering user: {}", userSignUp);
        service.save(userSignUp);
    }
    
}
