package org.banana.authserver.filters;

import org.banana.authserver.annotation.AllowAuth;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

@WebFilter(filterName = "LoggingFilter")
@Component
public class LoggingFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest)req;
        Principal user = httpReq.getUserPrincipal();
        if(user != null){
            System.out.println("User:" + user.getName() + " uri:" + httpReq.getRequestURI());
        }

        System.out.println("LoggingFilter, url:" + httpReq.getRequestURI());
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
