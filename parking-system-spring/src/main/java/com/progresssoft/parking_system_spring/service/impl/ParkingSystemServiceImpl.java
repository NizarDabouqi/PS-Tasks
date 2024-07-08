package com.progresssoft.parking_system_spring.service.impl;

import com.progresssoft.parking_system_spring.exceptions.ParkingSystemException;
import com.progresssoft.parking_system_spring.model.ParkingSystemResponse;
import com.progresssoft.parking_system_spring.model.entity.Employee;
import com.progresssoft.parking_system_spring.model.entity.ParkingSpot;
import com.progresssoft.parking_system_spring.repository.EmployeeRepository;
import com.progresssoft.parking_system_spring.repository.ParkingSpotRepository;
import com.progresssoft.parking_system_spring.service.ParkingSystemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ParkingSystemServiceImpl implements ParkingSystemService {

    private final ParkingSpotRepository parkingSpotRepository;

    private final EmployeeRepository employeeRepository;

    @Override
    public ParkingSystemResponse<Employee> addNewEmployee(Employee employee) {
        try {
            ParkingSystemResponse<Employee> response = this.getEmployeeById(employee.getId());
            if (response.getStatusCode().value() == 200) {
                throw new ParkingSystemException("Employee with id: %s already exists".formatted(employee.getId()));
            }
            employeeRepository.save(employee);
            return ParkingSystemResponse.<Employee>builder()
                    .statusCode(HttpStatus.CREATED)
                    .message("Employee successfully added")
                    .body(employee)
                    .build();
        } catch (final ParkingSystemException exception) {
            return ParkingSystemResponse.<Employee>builder()
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .message(exception.getMessage())
                    .build();
        }
    }

    @Override
    public ParkingSystemResponse<Employee> removeEmployee(String id) {
        try {
            final ParkingSystemResponse<Employee> response = this.getEmployeeById(id);
            if (response.getStatusCode().value() == 400) {
                throw new ParkingSystemException("Employee with id: %s does not exists".formatted(id));
            }
            this.removeEmployeeAssignment(id);
            employeeRepository.deleteById(id);
            return ParkingSystemResponse.<Employee>builder()
                    .statusCode(HttpStatus.OK)
                    .message("Employee successfully removed")
                    .body(response.getBody())
                    .build();
        } catch (final ParkingSystemException exception) {
            return ParkingSystemResponse.<Employee>builder()
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .message(exception.getMessage())
                    .build();
        }
    }

    @Override
    public ParkingSystemResponse<Employee> getEmployeeById(String id) {
        try {
            final Optional<Employee> response = employeeRepository.findById(id);
            final Employee employee = response.orElseThrow(
                    () -> new ParkingSystemException("No employee with id: %s found".formatted(id)));
            return ParkingSystemResponse.<Employee>builder()
                    .statusCode(HttpStatus.OK)
                    .message("Employee successfully retrieved")
                    .body(employee)
                    .build();
        } catch (final ParkingSystemException exception) {
            return ParkingSystemResponse.<Employee>builder()
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .message(exception.getMessage())
                    .build();
        }
    }

    @Override
    public ParkingSystemResponse<List<Employee>> getAllEmployees() {
        final List<Employee> employees = employeeRepository.findAll();
        return ParkingSystemResponse.<List<Employee>>builder()
                .statusCode(HttpStatus.OK)
                .message("Employees successfully retrieved")
                .body(employees)
                .build();
    }

    @Override
    public ParkingSystemResponse<ParkingSpot> addNewSpot(String id) {
        try {
            ParkingSystemResponse<ParkingSpot> response = this.getSpotById(id);
            if (response.getStatusCode().value() == 200) {
                throw new ParkingSystemException("Parking spot with id: %s already exists".formatted(id));
            }
            final ParkingSpot parkingSpot = ParkingSpot.builder().id(id).build();
            parkingSpotRepository.save(parkingSpot);
            return ParkingSystemResponse.<ParkingSpot>builder()
                    .statusCode(HttpStatus.CREATED)
                    .message("Parking spot successfully added")
                    .body(parkingSpot)
                    .build();
        } catch (final ParkingSystemException exception) {
            return ParkingSystemResponse.<ParkingSpot>builder()
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .message(exception.getMessage())
                    .build();
        }
    }

    @Override
    public ParkingSystemResponse<ParkingSpot> removeSpot(String id) {
        try {
            final ParkingSystemResponse<ParkingSpot> response = this.getSpotById(id);
            if (response.getStatusCode().value() == 400) {
                throw new ParkingSystemException("Parking spot with id: %s does not exists".formatted(id));
            }
            this.removeSpotAssignment(id);
            parkingSpotRepository.deleteById(id);
            return ParkingSystemResponse.<ParkingSpot>builder()
                    .statusCode(HttpStatus.OK)
                    .message("Parking spot successfully removed")
                    .body(response.getBody())
                    .build();
        } catch (final ParkingSystemException exception) {
            return ParkingSystemResponse.<ParkingSpot>builder()
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .message(exception.getMessage())
                    .build();
        }
    }

    @Override
    public ParkingSystemResponse<ParkingSpot> getSpotById(String id) {
        try {
            final Optional<ParkingSpot> response = parkingSpotRepository.findById(id);
            final ParkingSpot parkingSpot = response
                    .orElseThrow(() -> new ParkingSystemException("No parking spot with id: %s found".formatted(id)));
            return ParkingSystemResponse.<ParkingSpot>builder()
                    .statusCode(HttpStatus.OK)
                    .message("Parking spot successfully retrieved")
                    .body(parkingSpot)
                    .build();
        } catch (final ParkingSystemException exception) {
            return ParkingSystemResponse.<ParkingSpot>builder()
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .message(exception.getMessage())
                    .build();
        }
    }


    @Override
    public ParkingSystemResponse<List<ParkingSpot>> getAllSpots() {
        final List<ParkingSpot> parkingSpots = parkingSpotRepository.findAll();
        return ParkingSystemResponse.<List<ParkingSpot>>builder()
                .statusCode(HttpStatus.OK)
                .message("Parking spots successfully retrieved")
                .body(parkingSpots)
                .build();
    }

    @Override
    public ParkingSystemResponse<ParkingSpot> assignSpotToEmployee(String parkingSpotId, String employeeId, LocalDate expiryDate) {
        try {
            final ParkingSystemResponse<ParkingSpot> parkingSpotResponse = this.getSpotById(parkingSpotId);
            if (parkingSpotResponse.getStatusCode().value() == 400) {
                throw new ParkingSystemException("Parking spot with id: %s does not exists".formatted(parkingSpotId));
            }
            final ParkingSystemResponse<Employee> employeeResponse = this.getEmployeeById(employeeId);
            if (employeeResponse.getStatusCode().value() == 400) {
                throw new ParkingSystemException("Employee with id: %s does not exists".formatted(employeeId));
            }
            if (this.isEmployeeAssigned(employeeId)) {
                throw new ParkingSystemException("Employee with id: %s is already assigned to a parking spot".formatted(employeeId));
            }
            if (!parkingSpotResponse.getBody().isAvailable()) {
                throw new ParkingSystemException("Parking spot with id: %s is not available".formatted(parkingSpotId));
            }
            final ParkingSpot parkingSpot = this.updateParkingSpotInfo(parkingSpotId, employeeId, expiryDate);
            return ParkingSystemResponse.<ParkingSpot>builder()
                    .statusCode(HttpStatus.OK)
                    .message("Employee successfully assigned to parking spot")
                    .body(parkingSpot)
                    .build();
        } catch (ParkingSystemException exception) {
            return ParkingSystemResponse.<ParkingSpot>builder()
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .message(exception.getMessage())
                    .build();
        }
    }

    @Override
    public ParkingSystemResponse<ParkingSpot> removeSpotAssignment(String parkingSpotId) {
        try {
            final ParkingSystemResponse<ParkingSpot> parkingSpotResponse = this.getSpotById(parkingSpotId);
            if (parkingSpotResponse.getStatusCode().value() == 400) {
                throw new ParkingSystemException("Parking spot with id: %s does not exists".formatted(parkingSpotId));
            }
            final ParkingSpot parkingSpot = this.updateParkingSpotInfo(parkingSpotId, null, null);
            return ParkingSystemResponse.<ParkingSpot>builder()
                    .statusCode(HttpStatus.OK)
                    .message("Successfully removed assignment to parking spot")
                    .body(parkingSpot)
                    .build();
        } catch (ParkingSystemException exception) {
            return ParkingSystemResponse.<ParkingSpot>builder()
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .message(exception.getMessage())
                    .build();
        }
    }

    private void removeEmployeeAssignment(String employeeId) {
        this.getEmployeeById(employeeId);
        this.getEmployeeParkingSpot(employeeId)
                .ifPresent((parkingSpot -> removeSpotAssignment(parkingSpot.getId())));
    }

    private boolean isEmployeeAssigned(String employeeId) {
        return !parkingSpotRepository.findByEmployeeId(employeeId).isEmpty();
    }

    private Optional<ParkingSpot> getEmployeeParkingSpot(String employeeId) {
        final List<ParkingSpot> parkingSpots = parkingSpotRepository.findByEmployeeId(employeeId);
        if (parkingSpots.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(parkingSpots.get(1));
    }

    private ParkingSpot updateParkingSpotInfo(String parkingSpotId, String employeeId, LocalDate expiryDate) {
        final ParkingSpot parkingSpot = ParkingSpot.builder()
                .id(parkingSpotId)
                .employeeId(employeeId)
                .expiryDate(expiryDate)
                .build();
        parkingSpotRepository.save(parkingSpot);
        return parkingSpot;
    }

}
