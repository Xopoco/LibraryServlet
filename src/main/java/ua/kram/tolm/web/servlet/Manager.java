package ua.kram.tolm.web.servlet;

import org.apache.log4j.Logger;
import ua.kram.tolm.web.action.Action;
import ua.kram.tolm.web.action.ActionHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/manager")
public class Manager extends HttpServlet {
    private static final long serialVersionUID = 7002207372653790291L;
    private static final Logger LOG = Logger.getLogger(Manager.class);


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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("doGet");
        perform(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LOG.info("doPost");
        perform(req, resp);
    }

    private void perform (HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String actionName = req.getParameter("action");
        Action action = ActionHolder.getCommand(actionName);
        try {
            String view = action.execute(req, resp);

            req.getRequestDispatcher(view).forward(req, resp);
        } catch (ServletException | IOException e) {
            LOG.error("Some error : ", e);
        }
    }

}
