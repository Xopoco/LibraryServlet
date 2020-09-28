package ua.kram.tolm.web.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Action {

    public abstract String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;

}
