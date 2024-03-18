package com.progressoft.utility;

import com.progressoft.manager.ParkingSystemManager;
import com.progressoft.model.Employee;
import com.progressoft.model.ParkingLot;

import java.util.Collection;
import java.util.Map;

public class ParkingSystemConsoleUtility {
    public static void showSpots(ParkingSystemManager<Map<String, ParkingLot>, Map<String, Employee>> parkingSystemManager) {
        System.out.println("\nParking Lots:");
        Collection<ParkingLot> values = parkingSystemManager.getAllParkingLots().values();
        for (ParkingLot parkingLot : values) {
            System.out.println("Parking ID: " + parkingLot.getId() + ", Available: " + parkingLot.isAvailable() + ", Employee ID: " + parkingLot.getEmployeeId());
        }
    }

    public static void showEmployees(ParkingSystemManager<Map<String, ParkingLot>, Map<String, Employee>> parkingSystemManager) {
        System.out.println("\nEmployees Information:");
        Collection<Employee> values = parkingSystemManager.getAllEmployees().values();
        for (Employee employee : values) {
            System.out.println("Employee ID: " + employee.getId() + ", Employee Name: " + employee.getName());
        }
    }
}
