package com.oranfish.sushiweb.filter;

import com.oranfish.sushiservice.cache.SessionCache;
import com.oranfish.sushiweb.container.SessionContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "loginFilter",urlPatterns = "/*")
public class F1LoginFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(F1LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;
        Cookie[] cookies =  req.getCookies();
        String userToken = null;
        SessionCache sessionCache = null;
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("ut".equals(cookie.getName())){
                    userToken = cookie.getValue();
                }
            }
        }
//        if(userToken != null){
//            try {
//                sessionCache = RedisUtil.get(userToken, SessionCache.class);
//            } catch (Exception e) {
//                log.error(e.getMessage(), e);
//            }
//            if(sessionCache != null){
//                SessionContainer.setSession(sessionCache);
//            }
//        }
        chain.doFilter(request, response);
        SessionContainer.clear();
    }

    @Override
    public void destroy() {
    }
}
