package com.progressoft.quartz.servlet;

import com.progressoft.exception.ParkingSystemException;
import com.progressoft.model.Employee;
import com.progressoft.quartz.manager.Manager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/Employee"}, name = "EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("employeeId");
        String employeeName = req.getParameter("employeeName");
        Employee employee = new Employee(employeeId, employeeName);

        try {
            Manager.getParkingManager().addNewEmployee(employee);
        } catch (ParkingSystemException e) {
            req.setAttribute("errorMessage", "Could not save the employee: " + e.getMessage());
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("employeePage.jsp");
        requestDispatcher.forward(req, resp);
    }
}
