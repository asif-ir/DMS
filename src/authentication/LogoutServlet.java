package authentication;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by rawlooa on 7/17/2017.
 */
@WebServlet(
        name = "LogoutServlet",
        urlPatterns = {"/logout"}
)
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        try {
            session.setAttribute("username", null);
            session.invalidate();
        } catch (NullPointerException e) {
            e.printStackTrace();
            request.setAttribute("message",
                    "<p style='color : orange;'> Session timed out!</p>"
            );
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        request.setAttribute("message",
                "<p style='color : orange;'> Successfully logged out!</p>"
        );
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
