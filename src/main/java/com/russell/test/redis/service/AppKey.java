package com.russell.test.redis.service;


public enum AppKey {
    
    /** global:next_userId */
    GLOBAL_NEXT_USERID("global:next_userId"),
    /** user */
    USER("user"),
    /** users */
    USERS(" "),
    /** following: */
    FOLLOWING("following:");
    
    private final static String keyPattern = "%s%s";
    public final String key;
    
    
    AppKey(String key) {
        this.key = key;
    }
    
    public static String following(String userId) {
        return getKey(FOLLOWING, userId);
    }

    private static String getKey(AppKey appKey, String id) {
        return String.format(keyPattern, appKey.key, id);
    }
}
