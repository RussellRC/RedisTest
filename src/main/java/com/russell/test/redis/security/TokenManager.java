package com.russell.test.redis.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenManager {

    TokenInfo createNewToken(UserDetails userContext);

    UserDetails getUserDetails(String token);

    UserDetails removeToken(String token);

    void removeUserDetails(UserDetails userDetails);

    Collection<TokenInfo> getUserTokens(UserDetails userDetails);

    Map<String, UserDetails> getValidUsers();

}
