package com.oranfish.sushiweb.filter;

import com.oranfish.sushiservice.cache.SessionCache;
import com.oranfish.sushiservice.service.UUserService;
import com.oranfish.sushiutil.util.RedisUtil;
import com.oranfish.sushiweb.container.SessionContainer;
import com.oranfish.sushiweb.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "backendFilter",urlPatterns = "/back/*")
public class F2BackendFilter implements Filter {

    private static final String LOGIN_URI = "/back/login";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;
        if(!LOGIN_URI.equals(req.getRequestURI()) && (SessionContainer.get() == null || SessionContainer.get().getUserType() != 0)){
            HttpServletResponse res = (HttpServletResponse)response;
            res.sendRedirect(LOGIN_URI);
            return;
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
