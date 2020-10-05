package ua.kram.tolm.web.filter;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.BooksDAO;
import ua.kram.tolm.db.UserDAO;
import ua.kram.tolm.db.entity.User;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.Link;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter("/*")
public class AuthFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.info("AuthFilter#doFilter");
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        final HttpSession session = req.getSession();

        //Logged user.
        if (nonNull(session.getAttribute("login")) && nonNull(session.getAttribute("password"))) {
            LOG.debug("session has user login and pass...");

        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
