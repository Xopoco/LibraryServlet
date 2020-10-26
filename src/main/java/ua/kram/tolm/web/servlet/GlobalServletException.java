package ua.kram.tolm.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/globalError")
public class GlobalServletException extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Wronggggg');");
        out.println("</script>");
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Wrong post');");
        out.println("</script>");
    }

}
