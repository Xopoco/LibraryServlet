package ua.kram.tolm.web.command;

import org.apache.log4j.Logger;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.Link;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command {
    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("LogoutCommand#execute");

        HttpSession session = req.getSession(false);

        if (session != null) {
            session.removeAttribute("userId");
            session.removeAttribute("login");
            session.removeAttribute("roleId");
        }

        return Link.MAIN;
    }
}
