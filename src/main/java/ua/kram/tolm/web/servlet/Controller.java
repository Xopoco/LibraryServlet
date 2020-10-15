package ua.kram.tolm.web.servlet;

import org.apache.log4j.Logger;
import ua.kram.tolm.exception.GlobalException;
import ua.kram.tolm.web.command.Command;
import ua.kram.tolm.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manager")
public class Controller extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    public void init() {
        LOG.info("init");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        LOG.info("service");
        resp.getWriter().write("ALLOHA #servise");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        LOG.info("doGet");

        String commandName = req.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);
        try {
            String view = command.execute(req, resp);

            req.getRequestDispatcher(view).forward(req, resp);
        } catch (ServletException | IOException | GlobalException e) {
            LOG.error("Some error : " + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        LOG.info("doPost");

        String commandName = req.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);
        try {
            String view = command.execute(req, resp);

            resp.sendRedirect(view);
        } catch (ServletException | IOException | GlobalException e) {
            LOG.error("Some error : " + e.getMessage(), e);
        }
    }

}
