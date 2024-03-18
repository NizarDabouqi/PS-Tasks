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

@WebServlet(value = {"/RemoveParkingSpot"}, name = "RemoveParkingSpotServlet")
public class RemoveParkingSpotServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parkingLotId = req.getParameter("parkingLotId");

        try {
            ParkingLot parkingLot = Manager.getParkingManager().findParkingLotById(parkingLotId);
            Manager.getParkingManager().removeSpot(parkingLot);
        } catch (ParkingSystemException e) {
            req.setAttribute("errorMessage", "Could not remove the parking spot: " + e.getMessage());
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("RemoveSpot.jsp");
        requestDispatcher.forward(req, resp);
    }
}
