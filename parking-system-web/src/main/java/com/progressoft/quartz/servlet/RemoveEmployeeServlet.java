package com.progressoft.quartz.servlet;

import com.progressoft.exception.ParkingSystemException;
import com.progressoft.model.Employee;
import com.progressoft.quartz.manager.Manager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/RemoveEmployee"}, name = "RemoveEmployeeServlet")
public class RemoveEmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("employeeId");

        try {
            Employee employee = Manager.getParkingManager().findEmployeeById(employeeId);
            Manager.getParkingManager().removeEmployee(employee);
        } catch (ParkingSystemException e) {
            req.setAttribute("errorMessage", "Could not remove the employee: " + e.getMessage());
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("removeEmployeePage.jsp");
        requestDispatcher.forward(req, resp);
    }
}
