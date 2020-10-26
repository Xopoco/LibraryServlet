package ua.kram.tolm.web.command.admin;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.dao.UserDAO;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateRoleCommand extends Command {
    private static final Logger LOG = Logger.getLogger(UpdateRoleCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, GlobalException {
        LOG.info("#execute");

        String userId = req.getParameter("userId");
        String roleId = req.getParameter("roleId");

        UserDAO.updateUserRole(Integer.parseInt(userId), Integer.parseInt(roleId));

        return "manager?command=manager";
    }
}
