package authentication;

import database.ConnectionFactory;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rawlooa on 7/17/2017.
 */
@WebServlet(
        name = "LoginServlet",
        urlPatterns = {"/login"}
)
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection conn = ConnectionFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("select username, privilege from users where username=? and password=?");

            ps.setString(1, request.getParameter("userName"));
            ps.setString(2, request.getParameter("password"));
            ResultSet rs = ps.executeQuery();

            HttpSession session = request.getSession(false);
            int loginAttempt = 0;

            try {
                loginAttempt = (Integer) session.getAttribute("loginCount");
            } catch (NullPointerException e) {
                session.setAttribute("loginCount", 0);
            }

            if (((System.currentTimeMillis() - session.getLastAccessedTime())/1000)>30)
                loginAttempt = 0;

            if (loginAttempt < 3) {
                if (rs.next()) {
                    RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
                    HttpSession sess = request.getSession(true);
                    sess.setAttribute("username", request.getParameter("userName"));
                    sess.setAttribute("privilege", rs.getString("privilege"));
                    sess.setMaxInactiveInterval(180); //  Timeout after 180 seconds.
                    rd.forward(request, response);
                } else {
                    session.setAttribute("loginCount", ++loginAttempt);
                    request.setAttribute("message",
                            "<p style='color : red;'> Invalid credentials!</p>"
                    );
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message",
                        "<p style='color : red;'> Login attempts finished, wait for 30 seconds to login!</p>"
                );
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
