package ua.kram.tolm.web.command;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.DBManager;
import ua.kram.tolm.db.UserDAO;
import ua.kram.tolm.db.entity.User;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.Link;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserCommand extends Command {
    private static final Logger LOG = Logger.getLogger(UpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, GlobalException {
        LOG.info("UpdateUserAction #execute");

        User u = (User) req.getAttribute("user");
        System.out.println(u);
        UserDAO.updateUser(u);

        new ShowCommand().execute(req, resp);
        return Link.ADMIN_PAGE_JSP;
    }
}
