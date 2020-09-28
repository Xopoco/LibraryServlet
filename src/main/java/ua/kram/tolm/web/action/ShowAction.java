package ua.kram.tolm.web.action;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.DBManager;
import ua.kram.tolm.db.entity.User;
import ua.kram.tolm.web.Link;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAction extends Action {
    private static Logger LOG = Logger.getLogger(ShowAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LOG.info("ShowAction execute is start");

        DBManager dbManager = DBManager.getInstance();

        List<User> users = dbManager.findAllUsers();
        req.setAttribute("users", users);


    return Link.ADMIN_PAGE_JSP;
    }
}
