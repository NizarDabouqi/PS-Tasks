package com.progressoft.quartz.servlet;

import com.progressoft.model.ParkingLot;
import com.progressoft.quartz.manager.Manager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


@WebServlet(value = {"/ParkingLotsInfo"}, name = "ParkingLotsInfoServlet")
public class ParkingLotsInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<ParkingLot> values = Manager.getParkingManager().getAllParkingLots().values();
        req.setAttribute("parkingLots", values);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("ParkingLotsInfo.jsp");
        requestDispatcher.forward(req, resp);
    }
}
