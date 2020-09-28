package ua.kram.tolm.web.action;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.DBManager;
import ua.kram.tolm.web.Link;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveUserAction extends Action {
    private static Logger LOG = Logger.getLogger(RemoveUserAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LOG.info("RemoveUserAction #execute is started");

        int id = Integer.parseInt(req.getParameter("id"));
        DBManager dbManager = DBManager.getInstance();
        dbManager.deleteUser(id);

        new ShowAction().execute(req, resp);

        return Link.ADMIN_PAGE_JSP;
    }
}
