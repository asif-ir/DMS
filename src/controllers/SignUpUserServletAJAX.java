package controllers;

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
 * Created by rawlooa on 7/19/2017.
 */
@WebServlet(name = "SignUpUserServletAJAX", urlPatterns = {"/sign-up-ajax"})
public class SignUpUserServletAJAX extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {
            Connection conn = ConnectionFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement("select username from users where username=?");
            ps.setString(1, request.getParameter("userName"));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.print("exists");
            } else {
                out.print("new");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
