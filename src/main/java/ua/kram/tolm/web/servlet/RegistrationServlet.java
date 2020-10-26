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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(name = "RegisServlet", urlPatterns = {"/RegisServlet"})
public class RegistrationServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(RegistrationServlet.class);

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

        try (PrintWriter out = resp.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            jsonEnt.put("backgroundColor", "#CC6666");

            String login = req.getParameter("login");
            if(checkLogin(jsonEnt, login)) {
                out.print(jsonEnt.toString());
                return;
            }

            String password = req.getParameter("password");
            if(checkPassword(jsonEnt, password)){
                out.print(jsonEnt.toString());
                return;
            }

            String firstName = req.getParameter("first_name");
            String lastName = req.getParameter("last_name");

            String email = req.getParameter("email");
            if(checkEmail(jsonEnt, email)) {
                out.print(jsonEnt.toString());
                return;
            }

            String telephone = req.getParameter("telephone");
            if(checkTelephone(jsonEnt, telephone)) {
                out.print(jsonEnt.toString());
                return;
            }

            UserDAO.insertUser(User.createUser(login, password, firstName, lastName, email, telephone));
            jsonEnt.put("result", "1");
            out.print(jsonEnt.toString());

        } catch (IOException | JSONException e) {
            LOG.error("#in json place");
            throw new GlobalException(e.getMessage());
        }
    }

    private boolean checkPassword(JSONObject jsonEnt, String password) throws JSONException {
        Pattern pattern =  Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.find()) {
            jsonEnt.put("serverInfo", "This password is invalid \n" +
                    "- a digit must occur at least once\n" +
                    "- a lower case letter must occur at least once\n" +
                    "- an upper case letter must occur at least once\n" +
                    "- no whitespace allowed in the entire string\n" +
                    "- at least 6 characters");
            return true;
        }

        return false;
    }

    private boolean checkLogin (JSONObject jsonEnt, String login) throws GlobalException, JSONException {
        Pattern pattern =  Pattern.compile("^(?=\\S+$).{3,19}$");
        Matcher matcher = pattern.matcher(login);
        if (!matcher.find()) {
            jsonEnt.put("serverInfo", "This login is invalid");
            return true;
        }

        User user = UserDAO.findUserByLogin(login);
        if (user != null) {
            jsonEnt.put("serverInfo", "This login is not available");
            return true;
        }
        return false;
    }

    private boolean checkEmail (JSONObject jsonEnt, String email) throws JSONException, GlobalException {
        Pattern pattern =  Pattern.compile("^[A-Z0-9_%+-]+@[A-Z0-9-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            jsonEnt.put("serverInfo", "This email is invalid");
            return true;
        }

        User user = UserDAO.findUserByEmail(email);

        if (user != null) {
            jsonEnt.put("serverInfo", "This email is not available");
            return true;
        }
        return false;
    }

    private boolean checkTelephone (JSONObject jsonEnt, String telephone) throws JSONException, GlobalException {
        Pattern pattern =  Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(telephone);
        if (!matcher.find()) {
            jsonEnt.put("serverInfo", "This telephone is invalid");
            return true;
        }

        User user = UserDAO.findUserByTelephone(telephone);
        if (user != null) {
            jsonEnt.put("serverInfo", "This telephone is not available");
            return true;
        }
        return false;
    }
}
