package ua.kram.tolm.web.action;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.DBManager;
import ua.kram.tolm.db.entity.User;
import ua.kram.tolm.web.Link;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterAction extends Action {
    private static Logger LOG = Logger.getLogger(RegisterAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LOG.info("RegisterAction #execute start");
        req.setCharacterEncoding("UTF8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");

        DBManager dbManager = DBManager.getInstance();
        dbManager.insertUser(User.createUser(login, password, firstName, lastName, email, telephone));

        new ShowAction().execute(req, resp);

        return Link.MAIN;
    }

}
