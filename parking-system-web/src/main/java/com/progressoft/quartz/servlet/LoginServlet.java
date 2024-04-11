package com.progressoft.quartz.servlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = {"/login"}, name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private static void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve from a servlet
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        // Send data to a JSP and redirect to the JSP
        req.setAttribute("username", username);
        req.setAttribute("password", password);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("");
        requestDispatcher.forward(req, resp);
    }
}
