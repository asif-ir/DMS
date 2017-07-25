package controllers;

import models.SparePart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rawlooa on 7/17/2017.
 */
@WebServlet(name = "AddSparePartServlet", urlPatterns = {"/add-spare-part"})
public class AddSparePartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean success = new SparePart().addSparePart(
                request.getParameter("name"),
                request.getParameter("model"),
                Integer.parseInt(request.getParameter("units")),
                Double.parseDouble(request.getParameter("price"))
        );
        if (success) {
            request.setAttribute("message",
                    "<p style='color : green;'> Spare part added successfully!</p>"
            );
        } else {
            request.setAttribute("message",
                    "<p style='color : red;'> Spare part already exists!</p>"
            );
        }
        request.getRequestDispatcher("add_spare_part.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
