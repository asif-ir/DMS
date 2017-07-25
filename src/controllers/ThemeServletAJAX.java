package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by rawlooa on 7/21/2017.
 */
@WebServlet(name = "ThemeServletAJAX", urlPatterns = {"/theme-ajax"})
public class ThemeServletAJAX extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie themeCookie = new Cookie("theme", request.getParameter("theme"));
        response.addCookie(themeCookie);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
