package com.oranfish.sushiweb.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "backendFilter",urlPatterns = "/back/*")
public class F2BackendFilter implements Filter {

    private static final String LOGIN_URI = "/back/login";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
//        HttpServletRequest req = (HttpServletRequest) request;
//        if(!LOGIN_URI.equals(req.getRequestURI()) && (SessionContainer.get() == null || SessionContainer.get().getUserType() != 0)){
//            HttpServletResponse res = (HttpServletResponse)response;
//            res.sendRedirect(LOGIN_URI);
//            return;
//        }else{
            chain.doFilter(request, response);
//        }
    }

    @Override
    public void destroy() {
    }
}
