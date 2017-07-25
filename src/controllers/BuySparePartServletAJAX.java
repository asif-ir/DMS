package controllers;

import models.SparePart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rawlooa on 7/23/2017.
 */
@WebServlet(name = "BuySparePartServletAJAX", urlPatterns = {"/buy-spare-part-ajax"})
public class BuySparePartServletAJAX extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ResultSet rs = new SparePart().getModels(request.getParameter("name"));

        try {
            out.print("<div class=\"form-group\" id='after-name' style='padding-top: 2%;'>\n" +
                    "        <label for=\"model\">Select Model:</label>\n" +
                    "        <select class=\"form-control\" id=\"model\" name=\"model\">");

            while (rs.next()) {
                System.out.println(rs.getString("model"));
                out.print("<option>"+ rs.getString("model") +"</option>");
            }

            out.print("        </select>\n" +
                    "    </div>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
