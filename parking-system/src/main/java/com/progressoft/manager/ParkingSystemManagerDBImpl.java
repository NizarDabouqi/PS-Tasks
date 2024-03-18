package com.progressoft.manager;

import com.progressoft.dao.DatabaseConnector;
import com.progressoft.exception.ParkingSystemException;
import com.progressoft.model.Employee;
import com.progressoft.model.ParkingLot;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ParkingSystemManagerDBImpl implements ParkingSystemManager<Map<String, ParkingLot>, Map<String, Employee>> {
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public ParkingSystemManagerDBImpl() {

    }

    @Override
    public void assignSpotToEmployee(ParkingLot parkingLot, Employee employee) {
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
        parkingLot.setEmployee(employee);
        parkingLot.setEmployeeId(employee.getId());
        parkingLot.setEmployeeName(employee.getName());
        parkingLot.setAvailable(false);
        DatabaseConnector.updateParkingLot(parkingLot);

        ParkingLot finalParkingLot = parkingLot;

        scheduler.schedule(() -> {
            try {
                // get all parking lots
                // check each parking and its expiry date
                // if the expiry date less than today's date or time
                // call removeSpotFromEmployee
                removeSpotFromEmployee(finalParkingLot);
            } catch (ParkingSystemException e) {
                e.printStackTrace();
            }
        }, 15, TimeUnit.MINUTES);
    }


    @Override
    public void removeSpotFromEmployee(ParkingLot parkingLot) {
        try {
            if (parkingLot == null) {
                throw new ParkingSystemException("Parking ID not found");
            }

            ParkingLot existingParkingLot = findParkingLotById(parkingLot.getId());
            if (existingParkingLot.getEmployeeId() == null) {
                throw new ParkingSystemException("No employee assigned to this parking");
            }

            existingParkingLot.setEmployee(null);
            existingParkingLot.setAvailable(true);
            DatabaseConnector.updateParkingLot(existingParkingLot);
        } catch (ParkingSystemException e) {
            throw e;
        }
    }

    @Override
    public void addNewEmployee(Employee employee) {
        Map<String, Employee> employees = getAllEmployees();
        if (employees.containsKey(employee.getId())) {
            throw new ParkingSystemException("Employee ID already exists");
        } else if (employee.getId() == null || employee.getId().isEmpty()) {
            throw new ParkingSystemException("Employee ID cannot be empty");
        } else if (employee.getName() == null || employee.getName().isEmpty()) {
            throw new ParkingSystemException("Employee name cannot be empty");
        }
        DatabaseConnector.insertEmployee(employee);
    }

    @Override
    public void addNewSpot(ParkingLot parkingLot) {
        Map<String, ParkingLot> parkingLots = getAllParkingLots();
        if (parkingLot.getId() == null || parkingLot.getId().isEmpty()) {
            throw new ParkingSystemException("Parking ID cannot be empty");
        }

        if (parkingLots.containsKey(parkingLot.getId())) {
            throw new ParkingSystemException("Parking ID already exists");
        }

        DatabaseConnector.insertParkingLot(parkingLot);
    }

    @Override
    public void removeSpot(ParkingLot parkingLot) {
        if (parkingLot == null) {
            throw new ParkingSystemException("Parking ID not found");
        }
        findParkingLotById(parkingLot.getId());
        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PARKING_LOTS WHERE id = ?");
            preparedStatement.setString(1, parkingLot.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ParkingLot findParkingLotById(String id) {
        if (id == null || id.isEmpty()) {
            throw new ParkingSystemException("Parking ID cannot be empty");

        }
        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PARKING_LOTS WHERE id = ?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String parkingLotId = resultSet.getString("id");
                boolean available = resultSet.getBoolean("available");
                String employeeId = resultSet.getString("employee_id");
                String employeeName = resultSet.getString("employee_name");

                ParkingLot parkingLot = new ParkingLot(parkingLotId);
                parkingLot.setAvailable(available);
                parkingLot.setEmployeeId(employeeId);
                parkingLot.setEmployeeName(employeeName);

//                if (employeeId != null && employeeName != null) {
//                    parkingLot.setEmployeeId(employeeId);
//                    parkingLot.setEmployeeName(employeeName);
//                }

                Employee employee = null;
                if (employeeId != null && employeeName != null) {
                    employee = new Employee(employeeId, employeeName);
                }
                parkingLot.setEmployee(employee);

                return parkingLot;
            } else {
                throw new ParkingSystemException("Parking ID not found");
            }
        } catch (SQLException e) {
            throw new ParkingSystemException("Error finding parking lot by ID");
        }
    }

    @Override
    public Employee findEmployeeById(String id) {
        if (id == null || id.isEmpty()) {
            throw new ParkingSystemException("Employee ID cannot be empty");
        }
        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEES WHERE id = ?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String employeeId = resultSet.getString("id");
                String employeeName = resultSet.getString("name");

                return new Employee(employeeId, employeeName);
            } else {
                throw new ParkingSystemException("Employee ID not found");
            }
        } catch (SQLException e) {
            throw new ParkingSystemException("Error finding employee by ID");
        }
    }

    @Override
    public Map<String, ParkingLot> getAllParkingLots() {
        // get the parking lots from the DB, and map it to a hashmap and return it

        Map<String, ParkingLot> parkingLots = new HashMap<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PARKING_LOTS");

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                boolean available = resultSet.getBoolean("available");
                String employeeId = resultSet.getString("employee_id");
                String employeeName = resultSet.getString("employee_name");

                Employee employee = new Employee(employeeId, employeeName);
                ParkingLot parkingLot = new ParkingLot(id);
                parkingLot.setEmployee(employee);
                parkingLot.setAvailable(available);

                parkingLots.put(id, parkingLot);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parkingLots;
    }

    @Override
    public Map<String, Employee> getAllEmployees() {
        // get the employees from the DB, and map it to a hashmap and return it

        Map<String, Employee> employees = new HashMap<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEES");

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");

                Employee employee = new Employee(id, name);
                employees.put(id, employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    ///////////////////////////////////////////////////Remove Employee///////////////////////////////////////////////////////////////////////////
    @Override
    public void removeEmployee(Employee employee) {
        try {
            if (employee == null) {
                throw new ParkingSystemException("Employee ID not found");
            }
            findEmployeeById(employee.getId());
            String parkingLotId = findParkingLotIdByEmployeeId(employee.getId());
            if (parkingLotId != null) {
                removeSpotFromEmployee(getAllParkingLots().get(parkingLotId));
            }
            deleteEmployeeFromDB(employee.getId());
            getAllEmployees().remove(employee.getId());
        } catch (ParkingSystemException e) {
            throw e;
        }
    }

    private String findParkingLotIdByEmployeeId(String employeeId) {
        String parkingLotId = null;
        try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM PARKING_LOTS WHERE employee_id = ?")) {
            preparedStatement.setString(1, employeeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    parkingLotId = resultSet.getString("id");
                }
            }
        } catch (SQLException e) {
            throw new ParkingSystemException("Error finding parking lot ID by employee ID");
        }
        return parkingLotId;
    }

    private void deleteEmployeeFromDB(String employeeId) {
        try (Connection connection = DatabaseConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM EMPLOYEES WHERE id = ?")) {
            preparedStatement.setString(1, employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ParkingSystemException("Error deleting employee");
        }
    }
    ///////////////////////////////////////////////////Remove Employee///////////////////////////////////////////////////////////////////////////
}
