package ua.kram.tolm.web.command;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.DAO.UserDAO;
import ua.kram.tolm.db.entity.User;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.Link;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ShowCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("ShowAction execute is start");

        List<User> users = UserDAO.findAllUsers();
        req.setAttribute("users", users);

        return Link.ADMIN_PAGE_JSP;
    }
}
