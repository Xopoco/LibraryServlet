package ua.kram.tolm.web.command.Admin;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.UserDAO;
import ua.kram.tolm.db.entity.User;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.Link;
import ua.kram.tolm.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowUpdateUserCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ShowUpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("ShowUpdateUserAction #execute");

        String userId = req.getParameter("userId");
        User user = UserDAO.findUser(Integer.parseInt(userId));
        req.setAttribute("user", user);

        return Link.UPDATE_USER;
    }
}
