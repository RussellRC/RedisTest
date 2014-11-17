package com.russell.test.redis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.russell.test.redis.app.User;
import com.russell.test.redis.dao.UserRepositoryImpl;
import com.russell.test.redis.security.impl.UserContext;


/**
 * Implements Spring Security {@link UserDetailsService} that is injected into authentication provider in configuration XML.
 * It interacts with domain, loads user details and wraps it into {@link UserContext} which implements Spring Security {@link UserDetails}.
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    
    
    @Autowired
    private UserRepositoryImpl userDao;

    /**
     * This will be called from
     * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider#retrieveUser(java.lang.String, org.springframework.security.authentication.UsernamePasswordAuthenticationToken)}
     * when {@link AuthenticationService#authenticate(java.lang.String, java.lang.String)} calls
     * {@link AuthenticationManager#authenticate(org.springframework.security.core.Authentication)}. Easy.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("loadUserByEmail for {}", username);
        User user = userDao.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return new UserContext(user);
    }
}
