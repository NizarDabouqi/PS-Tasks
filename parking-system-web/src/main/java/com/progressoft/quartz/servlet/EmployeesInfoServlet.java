package com.progressoft.quartz.servlet;

import com.progressoft.model.Employee;
import com.progressoft.quartz.manager.Manager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(value = {"/EmployeesInfo"}, name = "EmployeesInfoServlet")
public class EmployeesInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Employee> values = Manager.getParkingManager().getAllEmployees().values();
        req.setAttribute("employees", values);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("employeesInfoPage.jsp");
        requestDispatcher.forward(req, resp);

    }
}
