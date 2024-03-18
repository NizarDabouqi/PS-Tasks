package com.progressoft.quartz.servlet;

import com.progressoft.exception.ParkingSystemException;
import com.progressoft.model.Employee;
import com.progressoft.model.ParkingLot;
import com.progressoft.quartz.manager.Manager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/assignSpotToEmployee"}, name = "assignSpotToEmployeeServlet")
public class assignSpotToEmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parkingLotId = req.getParameter("parkingLotId");
        String employeeId = req.getParameter("employeeId");

        try {
            ParkingLot parkingLot = Manager.getParkingManager().findParkingLotById(parkingLotId);
            Employee employee = Manager.getParkingManager().findEmployeeById(employeeId);

            Manager.getParkingManager().assignSpotToEmployee(parkingLot, employee);
        } catch (ParkingSystemException e) {
            req.setAttribute("errorMessage", "Could not assign the parking spot to employee: " + e.getMessage());
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("AssignPage.jsp");
        requestDispatcher.forward(req, resp);
    }
}

