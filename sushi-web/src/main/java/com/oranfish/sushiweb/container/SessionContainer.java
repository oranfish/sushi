package com.oranfish.sushiweb.container;

import com.oranfish.sushiservice.cache.SessionCache;

public class SessionContainer {
    private static final ThreadLocal<SessionCache> threadLocal = new ThreadLocal<>();

    public static SessionCache get() {
        return ((SessionCache)threadLocal.get());
    }

    public static void setSession(SessionCache sessionCache) {
        if (threadLocal.get() == null) {
            threadLocal.set(sessionCache);
        }

    }

    public static void clear() {
        threadLocal.set(null);
    }
}
