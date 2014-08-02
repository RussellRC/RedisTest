package com.russell.test.redis.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Repository;

import com.russell.test.redis.bo.ServiceUser;
import com.russell.test.redis.bo.User;
import com.russell.test.redis.service.AppKey;

import static com.russell.test.redis.service.AppKey.*;

@Repository
public class UserRepositoryImpl {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String, ServiceUser> redisTemplate;
    private RedisAtomicLong userIdCounter; 
    
    @PostConstruct
    public void postConstruct() {
        userIdCounter = new RedisAtomicLong(GLOBAL_NEXT_USERID.key, stringRedisTemplate.getConnectionFactory());
    }
    
    public void save(ServiceUser user) {
        long userId = userIdCounter.incrementAndGet();
        user.setId(userId);
        
        redisTemplate.opsForHash().put(USER.key, String.valueOf(userId), user);
        stringRedisTemplate.opsForHash().put(USERS.key, user.getEmail(), String.valueOf(userId));
    }
    
    public User findByEmail(String email) {
        String userId = (String) stringRedisTemplate.opsForHash().get(USERS.key, email);
        return findById(userId);
    }

    public User findById(String id) {
        User user = (User) redisTemplate.opsForHash().get(USER.key, id);
        return user;
    }

    public void saveFollowing(String userId, String targetUserId) {
        stringRedisTemplate.opsForZSet().add(AppKey.following(userId), targetUserId, System.currentTimeMillis());
        
    }
}
