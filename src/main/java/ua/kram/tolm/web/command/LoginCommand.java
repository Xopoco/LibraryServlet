package ua.kram.tolm.web.command;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.dao.UserDAO;
import ua.kram.tolm.db.entity.User;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.Link;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand extends Command {
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("LoginCommand#execute");

        String login = req.getParameter("login");
        LOG.debug("Request parameter: login --> " + login);

        User user = UserDAO.findUserByLogin(login);

        if (user != null) {
            String password = req.getParameter("password");

            if (!password.equals(user.getPassword())){
                throw new GlobalException("Wrong password");
            }
            req.getSession().setAttribute("userId", user.getId());
            req.getSession().setAttribute("login", user.getLogin());
            req.getSession().setAttribute("roleId", user.getRoleId());
        } else {
            throw new GlobalException("This login incorrect");
        }

        return Link.MAIN;
    }
}
