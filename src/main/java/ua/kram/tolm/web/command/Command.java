package ua.kram.tolm.web.command;

import ua.kram.tolm.exception.GlobalException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {

    public abstract String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, GlobalException;

}
