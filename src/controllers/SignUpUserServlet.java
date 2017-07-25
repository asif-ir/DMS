package controllers;

import database.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by rawlooa on 7/17/2017.
 */
@WebServlet(name = "SignUpUserServlet", urlPatterns = {"/sign-up"})
public class SignUpUserServlet extends HttpServlet {
    Connection conn = ConnectionFactory.getConnection();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into users(username, password, privilege) values (?, ?, ?)");

            ps.setString(1, request.getParameter("userName"));
            ps.setString(2, request.getParameter("password"));
            ps.setInt(3,
                    request.getParameter("privilege")!=null ? Integer.parseInt(request.getParameter("privilege")) : 0
            );

            request.setAttribute("message",
                    "<p style='color : green;'> Registration successful</p>"
            );
            request.getRequestDispatcher("index.jsp").forward(request, response);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message",
                    "<p style='color : red;'> User already exists!</p>"
            );
            request.getRequestDispatcher("sign_up.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
