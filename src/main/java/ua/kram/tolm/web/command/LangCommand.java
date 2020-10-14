package ua.kram.tolm.web.command;

import org.apache.log4j.Logger;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.Link;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LangCommand extends Command{
    private static final Logger LOG = Logger.getLogger(LangCommand.class);


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, GlobalException {
        LOG.info("#execute");

        String value = req.getParameter("value");
        req.getSession().setAttribute("lang", value);

        return Link.MAIN;
    }
}
