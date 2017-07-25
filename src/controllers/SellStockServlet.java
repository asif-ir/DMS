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
@WebServlet(name = "SellStockServlet", urlPatterns = {"/sell-stock"})
public class SellStockServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean success = new SparePart().sellStock(
                request.getParameter("name"),
                request.getParameter("model"),
                Integer.parseInt(request.getParameter("units"))
        );
        if (success) {
            request.setAttribute("message",
                    "<p style='color : green;'> Spare part stock sold successfully!</p>"
            );
        } else {
            request.setAttribute("message",
                    "<p style='color : red;'> Spare part doesn't exist!</p>"
            );
        }
        request.getRequestDispatcher("sell_stock.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
