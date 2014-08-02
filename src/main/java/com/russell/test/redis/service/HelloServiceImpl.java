package com.russell.test.redis.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("helloService")
public class HelloServiceImpl implements HelloService {
    
    @Override
    @Cacheable(value="messageCache")
    public String getMessage(String name) {
        System.out.println("Executing => getMessage(\"" + name + "\")");
        return "Hello " + name + "!!!";
    }

    
    
}
