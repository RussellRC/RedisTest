package com.russell.test.redis.security;

import org.springframework.security.core.userdetails.UserDetails;


public interface AuthenticationService {

    void logout(String token);

    boolean checkToken(String token);

    TokenInfo authenticate(String username, String password);

    UserDetails currentUser();

}
