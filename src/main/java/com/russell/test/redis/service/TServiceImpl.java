package com.russell.test.redis.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.russell.test.redis.bo.ServiceUser;
import com.russell.test.redis.dao.UserRepositoryImpl;

@Service
public class TServiceImpl implements TService {

    @Resource
    private UserRepositoryImpl userRepository;
    
    @Override
    public void save(ServiceUser user) {
        userRepository.save(user);
    }

    @Override
    public ServiceUser findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public ServiceUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void follow(String userId, String targetUserId) {
        userRepository.saveFollowing(userId, targetUserId);
    }

}
