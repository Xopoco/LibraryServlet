package ua.kram.tolm.web.command.Admin;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.DAO.UserDAO;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.Link;
import ua.kram.tolm.web.command.Command;
import ua.kram.tolm.web.command.ShowCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveUserCommand extends Command {
    private static final Logger LOG = Logger.getLogger(RemoveUserCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException, IOException, ServletException {
        LOG.info("RemoveUserAction #execute is started");

        int id = Integer.parseInt(req.getParameter("id"));
        UserDAO.deleteUser(id);

        new ShowCommand().execute(req, resp);
        return Link.ADMIN_PAGE_JSP;
    }
}
