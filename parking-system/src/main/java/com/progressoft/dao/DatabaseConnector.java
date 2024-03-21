package com.progressoft.dao; // dao: Data Access Object

import com.progressoft.exception.ParkingSystemException;
import com.progressoft.model.Employee;
import com.progressoft.model.ParkingLot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/jdbc_parking_system";
    private static final String USERNAME = "nizar_sami";
    private static final String PASSWORD = "P@ssw0rd";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void insertEmployee(Employee employee) {
        PreparedStatement preparedStatement = null;

        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO EMPLOYEES (id, name) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertParkingLot(ParkingLot parkingLot) {
        PreparedStatement preparedStatement = null;

        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO PARKING_LOTS (id, available, employee_id, employee_name) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, parkingLot.getId());
            preparedStatement.setBoolean(2, parkingLot.isAvailable());
            preparedStatement.setString(3, parkingLot.getEmployeeId());
            preparedStatement.setString(4, parkingLot.getEmployeeName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateParkingLot(ParkingLot parkingLot) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE PARKING_LOTS SET available = ?, employee_id = ?, employee_name = ?, expiry_date = ? WHERE id = ?");


            if (parkingLot.getExpiryDate() != null) {
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(parkingLot.getExpiryDate(), inputFormatter);

                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String reversedDate = outputFormatter.format(date);

                preparedStatement.setString(4, reversedDate);
            } else {
                preparedStatement.setString(4, parkingLot.getExpiryDate());
            }

            preparedStatement.setBoolean(1, parkingLot.isAvailable());
            preparedStatement.setString(2, parkingLot.getEmployeeId());
            preparedStatement.setString(3, parkingLot.getEmployeeName());
            preparedStatement.setString(5, parkingLot.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new ParkingSystemException("Error updating parking lot in the database");
        }
    }
}



