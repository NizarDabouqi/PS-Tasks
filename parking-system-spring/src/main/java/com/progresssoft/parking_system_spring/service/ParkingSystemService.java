package com.progresssoft.parking_system_spring.service;

import com.progresssoft.parking_system_spring.model.ParkingSystemResponse;
import com.progresssoft.parking_system_spring.model.entity.Employee;
import com.progresssoft.parking_system_spring.model.entity.ParkingSpot;

import java.time.LocalDate;
import java.util.List;

public interface ParkingSystemService {
    ParkingSystemResponse<Employee> addNewEmployee(Employee employee);

    ParkingSystemResponse<Employee> removeEmployee(String id);

    ParkingSystemResponse<Employee> getEmployeeById(String id);

    ParkingSystemResponse<List<Employee>> getAllEmployees();

    ParkingSystemResponse<ParkingSpot> addNewSpot(String id);

    ParkingSystemResponse<ParkingSpot> removeSpot(String id);

    ParkingSystemResponse<ParkingSpot> getSpotById(String id);

    ParkingSystemResponse<List<ParkingSpot>> getAllSpots();

    ParkingSystemResponse<ParkingSpot> assignSpotToEmployee(String parkingSpotId, String employeeId, LocalDate expiryDate);

    ParkingSystemResponse<ParkingSpot> removeSpotAssignment(String parkingSpotId);
}
