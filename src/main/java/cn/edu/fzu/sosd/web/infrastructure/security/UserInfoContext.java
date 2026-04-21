package cn.edu.fzu.sosd.web.infrastructure.security;

import cn.edu.fzu.sosd.web.shared.security.CurrentUser;

/**
 * class name: UserInfoContext
 *
 * @author: dkb
 * @description: 用户信息上下文
 * @date: 2026/4/18 14:30
 */
public class UserInfoContext {
    private static final ThreadLocal<CurrentUser> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void clear() {
        USER_THREAD_LOCAL.remove();
    }

    public static CurrentUser getCurrentUser() {
        return USER_THREAD_LOCAL.get();
    }

    public static void setCurrentUser(CurrentUser user) {
        USER_THREAD_LOCAL.set(user);
    }
}
