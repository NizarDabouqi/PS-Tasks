package com.progressoft.manager;

import com.progressoft.exception.ParkingSystemException;
import com.progressoft.model.Employee;
import com.progressoft.model.ParkingLot;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ParkingSystemManagerImpl implements ParkingSystemManager<Map<String, ParkingLot>, Map<String, Employee>> {
    private final Map<String, ParkingLot> parkingLots;
    private final Map<String, Employee> employees;

    public ParkingSystemManagerImpl() {
        parkingLots = new HashMap<>();
        employees = new HashMap<>();
    }

    @Override
    public void assignSpotToEmployee(ParkingLot parkingLot, Employee employee, String expiryDate) {
        if (parkingLot == null) {
            throw new ParkingSystemException("Parking ID not found");
        } else {
            parkingLot = findParkingLotById(parkingLot.getId());
        }

        if (employee == null) {
            throw new ParkingSystemException("Employee ID not found");
        } else {
            findEmployeeById(employee.getId());
        }

        Employee existingEmployee = parkingLot.getEmployee();
        if (existingEmployee != null && existingEmployee.getId().equals(employee.getId())) {
            throw new ParkingSystemException("The employee is already assigned to this parking");
        } else if (existingEmployee != null) {
            throw new ParkingSystemException("The parking is assigned to another employee");
        }
        updateParkingLotInfo(parkingLot, employee, expiryDate);
    }

    private static void updateParkingLotInfo(ParkingLot parkingLot, Employee employee, String expiryDate) {
        parkingLot.setEmployee(employee);
        parkingLot.setEmployeeId(employee.getId());
        parkingLot.setEmployeeName(employee.getName());
        parkingLot.setAvailable(false);
        parkingLot.setExpiryDate(expiryDate);
    }

    @Override
    public void removeSpotFromEmployee(ParkingLot parkingLot) {
        parkingLot = findParkingLotById(parkingLot.getId());
        if (parkingLot.getEmployeeId() != null) {
            parkingLot.setEmployee(null);
            parkingLot.setAvailable(true);
            parkingLot.setExpiryDate(null);
        } else {
            throw new ParkingSystemException("No employee assigned to this parking");
        }
    }

    @Override
    public void addNewEmployee(Employee employee) {
        if (employees.containsKey(employee.getId())) {
            throw new ParkingSystemException("Employee ID already exists");
        } else if (employee.getId() == null || employee.getId().isEmpty()) {
            throw new ParkingSystemException("Employee ID cannot be empty");
        } else if (employee.getName() == null || employee.getName().isEmpty()) {
            throw new ParkingSystemException("Employee name cannot be empty");
        }
        employees.put(employee.getId(), new Employee(employee.getId(), employee.getName()));
    }

    @Override
    public void removeEmployee(Employee employee) {
        findEmployeeById(employee.getId());
        this.employees.remove(employee.getId());
        String parkingLotId = findParkingLotIdByEmployeeId(employee.getId());
        if (parkingLotId != null) {
            removeSpotFromEmployee(getAllParkingLots().get(parkingLotId));
        }
    }

    @Override
    public void addNewSpot(ParkingLot parkingLot) {
        if (parkingLots.containsKey(parkingLot.getId())) {
            throw new ParkingSystemException("Parking ID already exists");
        } else if (parkingLot.getId() == null || parkingLot.getId().isEmpty()) {
            throw new ParkingSystemException("Parking ID cannot be empty");
        }
        parkingLots.put(parkingLot.getId(), new ParkingLot(parkingLot.getId()));
    }

    @Override
    public void removeSpot(ParkingLot parkingLot) {
        findParkingLotById(parkingLot.getId());
        parkingLots.remove(parkingLot.getId());
    }

    @Override
    public ParkingLot findParkingLotById(String id) {
        if (id == null || id.isEmpty()) {
            throw new ParkingSystemException("Parking ID cannot be empty");
        } else {
            ParkingLot parkingLot = parkingLots.get(id);
            if (parkingLot == null) {
                throw new ParkingSystemException("Parking ID not found");
            }
            return parkingLot;
        }
    }

    @Override
    public Employee findEmployeeById(String id) {
        if (id == null || id.isEmpty()) {
            throw new ParkingSystemException("Employee ID cannot be empty");
        } else {
            Employee employee = employees.get(id);
            if (employee == null) {
                throw new ParkingSystemException("Employee ID not found");
            }
            return employee;
        }
    }

    @Override
    public Map<String, ParkingLot> getAllParkingLots() {
        return new HashMap<>(parkingLots);
    }

    @Override
    public Map<String, Employee> getAllEmployees() {
        return new HashMap<>(employees);
    }

    private String findParkingLotIdByEmployeeId(String employeeId) {
        for (ParkingLot parkingLot : parkingLots.values()) {
            if (parkingLot.getEmployeeId() != null && parkingLot.getEmployeeId().equals(employeeId)) {
                return parkingLot.getId();
            }
        }
        return null;
    }
}
