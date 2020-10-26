package ua.kram.tolm.web.command.admin;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.dao.UserDAO;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockCommand extends Command {
    private static final Logger LOG = Logger.getLogger(BlockCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("#execute");

        String userId = req.getParameter("userId");
        String blockId = req.getParameter("blockId");

        UserDAO.updateUserBlockStatus(Integer.parseInt(userId), Integer.parseInt(blockId));

        return "manager?command=manager";
    }
}
