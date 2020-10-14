package ua.kram.tolm.web.command.admin;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.dao.UserDAO;
import ua.kram.tolm.db.entity.User;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.Link;
import ua.kram.tolm.web.command.Command;
import ua.kram.tolm.web.command.ShowCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserCommand extends Command {
    private static final Logger LOG = Logger.getLogger(UpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("UpdateUserAction #execute");

        String userId = req.getParameter("userId");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        LOG.info("new user name --> " + firstName + lastName);
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");

        User u = User.createUser(login, password, firstName, lastName, email, telephone);
        u.setId(Integer.parseInt(userId));

        UserDAO.updateUser(u);

        return new ShowCommand().execute(req, resp);
    }
}
