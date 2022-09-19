package org.child.security.util;

import org.child.security.entity.LoginUser;

import java.util.HashMap;
import java.util.Map;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2022/09/09
 */
public class CacheUtil {

    private static final Map<String, LoginUser> USER_CACHE = new HashMap<>();

    public static void cache(String username, LoginUser user) {
        USER_CACHE.put(username, user);
    }

    public static LoginUser get(String username) {
        return USER_CACHE.get(username);
    }

}
