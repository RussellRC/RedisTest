package com.russell.test.redis.security.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.russell.test.redis.security.AuthenticationService;
import com.russell.test.redis.security.TokenInfo;
import com.russell.test.redis.security.TokenManager;

/**
 * Service responsible for all around authentication, token checks, etc. This class does not care
 * about HTTP protocol at all.
 */
public class AuthenticationServiceDefault implements AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceDefault.class);
    
    @Autowired
    private ApplicationContext applicationContext;

    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;

    public AuthenticationServiceDefault(AuthenticationManager authenticationManager, TokenManager tokenManager) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
    }

    @PostConstruct
    public void init() {
        logger.debug(" *** AuthenticationServiceImpl.init with: " + applicationContext);
    }

    @Override
    public TokenInfo authenticate(String login, String password) {
        logger.debug(" *** AuthenticationServiceImpl.authenticate");

        // Here principal=username, credentials=password
        Authentication authentication = new UsernamePasswordAuthenticationToken(login, password);
        try {
            authentication = authenticationManager.authenticate(authentication);
            // Here principal=UserDetails (UserContext in our case), credentials=null (security
            // reasons)
            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (authentication.getPrincipal() != null) {
                UserDetails userContext = (UserDetails) authentication.getPrincipal();
                TokenInfo newToken = tokenManager.createNewToken(userContext);
                if (newToken == null) {
                    return null;
                }
                return newToken;
            }
        } catch (AuthenticationException e) {
            logger.debug(" *** AuthenticationServiceImpl.authenticate - FAILED: " + e.toString());
        }
        return null;
    }

    @Override
    public boolean checkToken(String token) {
        System.out.println(" *** AuthenticationServiceImpl.checkToken");

        UserDetails userDetails = tokenManager.getUserDetails(token);
        if (userDetails == null) {
            return false;
        }

        UsernamePasswordAuthenticationToken securityToken = new UsernamePasswordAuthenticationToken(userDetails,
                                                                                                    null,
                                                                                                    userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(securityToken);

        return true;
    }

    @Override
    public void logout(String token) {
        UserDetails logoutUser = tokenManager.removeToken(token);
        System.out.println(" *** AuthenticationServiceImpl.logout: " + logoutUser);
        SecurityContextHolder.clearContext();
    }

    @Override
    public UserDetails currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return null;
    }
}