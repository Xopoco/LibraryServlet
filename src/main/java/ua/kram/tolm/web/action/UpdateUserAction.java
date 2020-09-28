package ua.kram.tolm.web.action;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.DBManager;
import ua.kram.tolm.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserAction extends Action {
    private static Logger LOG = Logger.getLogger(UpdateUserAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LOG.info("UpdateUserAction #execute");

        DBManager dbManager = DBManager.getInstance();
        int id = Integer.parseInt(req.getParameter("id"));
        return null;
    }
}
