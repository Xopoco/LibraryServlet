package ua.kram.tolm.web.servlet;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import ua.kram.tolm.db.dao.UserDAO;
import ua.kram.tolm.db.entity.User;
import ua.kram.tolm.exception.GlobalException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LOG.info("#doGet");
        try {
            processRequest(request, response);
        } catch (GlobalException ex) {
            LOG.error("#doGet error");
            throw new ServletException(ex.getMessage());
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LOG.info("#doPost");
        try {
            processRequest(request, response);
        } catch (GlobalException ex) {
            LOG.error("#doPost error");
            throw new ServletException(ex.getMessage());
        }
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws GlobalException {
        LOG.info("#process");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        String login = req.getParameter("login");
        LOG.debug("Request parameter: login --> " + login);
        String password = req.getParameter("password");

        User user = UserDAO.findUserByLogin(login);
        JSONObject jsonEnt = new JSONObject();

        try (PrintWriter out = resp.getWriter()) {
            if (user != null && password.equals(user.getPassword()) && user.getBlockStatus() == 0) {
                    jsonEnt.put("result", "1");
                    req.getSession().setAttribute("userId", user.getId());
                    req.getSession().setAttribute("login", user.getLogin());
                    req.getSession().setAttribute("roleId", user.getRoleId());
            } else if (user != null && password.equals(user.getPassword()) &&user.getBlockStatus() != 0){
                jsonEnt.put("backgroundColor", "#CC6666");
                jsonEnt.put("serverInfo", "User is blocked");
            } else {
                jsonEnt.put("backgroundColor", "#CC6666");
                jsonEnt.put("serverInfo", "Invalid username or password");
            }
            out.print(jsonEnt.toString());
        } catch (IOException | JSONException e) {
            LOG.error("#in json place");
            throw new GlobalException(e.getMessage());
        }
    }

}

