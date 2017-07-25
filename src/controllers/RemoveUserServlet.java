package controllers;

import models.SparePart;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rawlooa on 7/21/2017.
 */
@WebServlet(name = "RemoveUserServlet", urlPatterns = {"/remove-user"})
public class RemoveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean success = new User().removeUser(request.getParameter("username"));
        if (success) {
            request.setAttribute("message",
                    "<p style='color : green;'> User removed successfully!</p>"
            );
        } else {
            request.setAttribute("message",
                    "<p style='color : red;'> User not present!</p>"
            );
        }
        request.getRequestDispatcher("terminate_operator.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
