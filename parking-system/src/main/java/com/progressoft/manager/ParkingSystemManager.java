package com.progressoft.manager;

import com.progressoft.model.Employee;
import com.progressoft.model.ParkingLot;

public interface ParkingSystemManager<E, T> {
    void assignSpotToEmployee(ParkingLot parkingLot, Employee employee);

    void removeSpotFromEmployee(ParkingLot parkingLot);

    void addNewEmployee(Employee employee);

    void removeEmployee(Employee employee);

    void addNewSpot(ParkingLot parkingLot);

    void removeSpot(ParkingLot parkingLot);

    ParkingLot findParkingLotById(String id);

    Employee findEmployeeById(String id);

    E getAllParkingLots();

    T getAllEmployees();
}
