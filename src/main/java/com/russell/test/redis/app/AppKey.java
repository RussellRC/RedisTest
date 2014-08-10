package com.russell.test.redis.app;


public enum AppKey {
    
    /** global:next_userId */
    GLOBAL_NEXT_USERID("global:next_userId"),
    /** user */
    USER("user:"),
    /** users */
    USERS("users"),
    /** following: */
    FOLLOWING("following:");
    
    private final static String keyPattern = "%s%s";
    public final String key;
    
    
    AppKey(String key) {
        this.key = key;
    }
    
    private static String formatKey(AppKey appKey, String id) {
        return String.format(keyPattern, appKey.key, id);
    }

	public static String user() {
		return USER.key;
	}
    
    public static String following(String userId) {
        return formatKey(FOLLOWING, userId);
    }

	public static String users() {
		return USERS.key;
	}

}
