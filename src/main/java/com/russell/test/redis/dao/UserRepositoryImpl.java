package com.russell.test.redis.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Repository;

import com.russell.test.redis.app.AppKey;
import com.russell.test.redis.app.User;
import com.russell.test.redis.bo.ServiceUser;

import static com.russell.test.redis.app.AppKey.*;

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
        
        redisTemplate.opsForHash().put(AppKey.user(), String.valueOf(userId), user);
        stringRedisTemplate.opsForHash().put(AppKey.users(), user.getEmail(), String.valueOf(userId));
    }
    
    public ServiceUser findByEmail(String email) {
        if (email == null) {
            return null;
        }
    	String userId = (String) stringRedisTemplate.opsForHash().get(AppKey.users(), email.toLowerCase());
        return findById(userId);
    }

    public ServiceUser findById(String id) {
        if (id == null) {
            return null;
        }
    	ServiceUser user = (ServiceUser) redisTemplate.opsForHash().get(AppKey.user(), id);
        return user;
    }
    
    public boolean auth(String email, String password) {
    	User user = findByEmail(email);
    	if (user == null) {
    		return false;
    	}
    	
    	return user.getPassword().equals(password);
    }

    public void saveFollowing(String userId, String targetUserId) {
        stringRedisTemplate.opsForZSet().add(AppKey.following(userId), targetUserId, System.currentTimeMillis());        
    }
}
