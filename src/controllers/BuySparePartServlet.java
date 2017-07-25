package controllers;

import models.SparePart;
import utils.PDFStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rawlooa on 7/23/2017.
 */
@WebServlet(name = "BuySparePartServlet", urlPatterns = {"/buy-spare-part"})
public class BuySparePartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean success = new SparePart().sellStock(
                request.getParameter("name"),
                request.getParameter("model"),
                Integer.parseInt(request.getParameter("units"))
        );

        if (success) {
            double cost = new SparePart().getPrice(
                    request.getParameter("name"),
                    request.getParameter("model")
            );

            // Generating bill.
            new PDFStore().create(
                    request.getParameter("name"),
                    request.getParameter("model"),
                    Double.parseDouble(request.getParameter("units")) * cost
            );

            request.setAttribute("message",
                    "<p style='color : green;'> Transaction Successful!</p>"
            );
            request.setAttribute("bill_button",
                    "<a>The bill has been generated by the server</a>"
            );
        } else {
            request.setAttribute("message",
                    "<p style='color : red;'>Out of stock!</p>"
            );
        }
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}