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


@WebServlet(value = {"/ParkingLotInfo"}, name = "ParkingLotInfoServlet")
public class ParkingLotInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parkingId = req.getParameter("parkingId");

        try {
            ParkingLot parkingLot = Manager.getParkingManager().findParkingLotById(parkingId);

            req.setAttribute("parkingLotId", parkingLot.getId());
            req.setAttribute("isAvailable", parkingLot.isAvailable());

            if (parkingLot.getEmployeeId() == null) {
                req.setAttribute("employeeId", "null");
            } else {
                req.setAttribute("employeeId", parkingLot.getEmployeeId());
            }

            if (parkingLot.getEmployeeName() == null) {
                req.setAttribute("employeeName", "null");
            } else {
                req.setAttribute("employeeName", parkingLot.getEmployeeName());
            }
        } catch (ParkingSystemException e) {
            req.setAttribute("errorMessage", "Could not show the parking spot information: " + e.getMessage());
        }


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("ParkingLotInfo.jsp");
        requestDispatcher.forward(req, resp);
    }
}
