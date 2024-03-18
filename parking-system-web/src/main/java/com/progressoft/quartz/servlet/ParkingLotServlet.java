package com.progressoft.quartz.servlet;

import com.progressoft.exception.ParkingSystemException;
import com.progressoft.model.ParkingLot;
import com.progressoft.quartz.manager.Manager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/ParkingLot"}, name = "ParkingLotServlet")
public class ParkingLotServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parkingLotId = req.getParameter("parkingLotId");
        ParkingLot parkingLot = new ParkingLot(parkingLotId);

        try {
            Manager.getParkingManager().addNewSpot(parkingLot);
        } catch (ParkingSystemException e) {
            req.setAttribute("errorMessage", "Could not save the parking spot: " + e.getMessage());
        }

        // Send data to a JSP and redirect to the JSP
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("ParkingLot.jsp");
        requestDispatcher.forward(req, resp);
    }
}
