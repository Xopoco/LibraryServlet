package ua.kram.tolm.web.command.admin;

import org.apache.log4j.Logger;
import ua.kram.tolm.db.dao.UserDAO;
import ua.kram.tolm.db.entity.User;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.Link;
import ua.kram.tolm.web.command.Command;
import ua.kram.tolm.web.command.ShowCommand;
import ua.kram.tolm.web.command.ShowSettingsCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class UpdateUserCommand extends Command {
    private static final Logger LOG = Logger.getLogger(UpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("UpdateUserAction #execute");



        return "manager?command=showUpdateUser";
    }
}
