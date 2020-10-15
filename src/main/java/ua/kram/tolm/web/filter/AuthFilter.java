package ua.kram.tolm.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter("/*")
public class AuthFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(AuthFilter.class);


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.info("AuthFilter#doFilter");


        chain.doFilter(request, response);
    }

}
