package com.progressoft.utility;

import com.progressoft.manager.ParkingSystemManagerDBImpl;

import com.progressoft.model.Employee;
import com.progressoft.model.ParkingLot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        ArrayList<String> names = new ArrayList<>();
//
//        names.add("Nizar");
//        names.add("George");
//        names.add("Yard");
//        names.add("Diana");
//        names.add("Sarah");
//
//        HashMap<String, ParkingLot> random = new HashMap<>();
//
//        random.put("a2", new ParkingLot("a2"));
//        random.put("a5", new ParkingLot("a5"));
//        random.put("a6", new ParkingLot("a6"));
//        random.put("a7", new ParkingLot("a7"));
//
//        random.forEach((id, parkingLot)  -> {
//
//
//        } );


        ParkingSystemManagerDBImpl parkingSystemManagerDB = new ParkingSystemManagerDBImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Assign a spot to an employee");
            System.out.println("2. Remove a spot from an employee");
            System.out.println("3. Show parking spots");
            System.out.println("4. Add a new parking spot");
            System.out.println("5. Add a new employee");
            System.out.println("6. Remove an employee");
            System.out.println("7. Remove a parking spot");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.next();
            scanner.nextLine();
            try {
                switch (choice) {
                    case "1":
                        System.out.println("Enter parking lot ID:");
                        String parkingLotId = scanner.nextLine();
                        System.out.println("Enter employee ID:");
                        String employeeId = scanner.nextLine();
                        System.out.println("Enter parking lot expiry date: (yyyy-MM-dd");
                        String expiryDate = scanner.nextLine();

                        parkingSystemManagerDB.assignSpotToEmployee(parkingSystemManagerDB.getAllParkingLots().get(parkingLotId), parkingSystemManagerDB.getAllEmployees().get(employeeId), expiryDate);
                        break;
                    case "2":
                        System.out.println("Enter parking lot ID to remove spot:");
                        String parkingLotIdToRemove = scanner.nextLine();
                        parkingSystemManagerDB.removeSpotFromEmployee(parkingSystemManagerDB.getAllParkingLots().get(parkingLotIdToRemove));
                        break;
                    case "3":
                        ParkingSystemConsoleUtility.showSpots(parkingSystemManagerDB);
                        break;
                    case "4":
                        System.out.println("Enter new parking lot ID to add:");
                        String newParkingLotId = scanner.nextLine();
                        parkingSystemManagerDB.addNewSpot(new ParkingLot(newParkingLotId));
                        break;
                    case "5":
                        System.out.println("Enter new employee ID:");
                        String newEmployeeId = scanner.nextLine();
                        System.out.println("Enter new employee name:");
                        String newEmployeeName = scanner.nextLine();
                        parkingSystemManagerDB.addNewEmployee(new Employee(newEmployeeId, newEmployeeName));
                        break;
                    case "6":
                        System.out.println("Enter employee ID to remove:");
                        String employeeIdToRemove = scanner.nextLine();
                        parkingSystemManagerDB.removeEmployee(parkingSystemManagerDB.getAllEmployees().get(employeeIdToRemove));
                        break;
                    case "7":

                        System.out.println("Enter parking lot ID to remove:");
                        String parkingLotIdToRemov = scanner.nextLine();
                        parkingSystemManagerDB.removeSpot(parkingSystemManagerDB.getAllParkingLots().get(parkingLotIdToRemov));
                        break;
                    case "8":
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Invalid choice. Please enter a number between 1 and 8.\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
