package com.progresssoft.parking_system_spring.controller;

import com.progresssoft.parking_system_spring.model.ParkingSystemResponse;
import com.progresssoft.parking_system_spring.model.entity.Employee;
import com.progresssoft.parking_system_spring.model.entity.ParkingSpot;
import com.progresssoft.parking_system_spring.service.ParkingSystemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/parking")
@AllArgsConstructor
public class ParkingSystemController {

    private final ParkingSystemService service;

    @PostMapping("/add-spot")
    public ResponseEntity<ParkingSystemResponse<ParkingSpot>> addSpot(@RequestBody String id) {
        final ParkingSystemResponse<ParkingSpot> response = service.addNewSpot(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/remove-spot/{id}")
    public ResponseEntity<ParkingSystemResponse<ParkingSpot>> removeSpot(@PathVariable String id) {
        final ParkingSystemResponse<ParkingSpot> response = service.removeSpot(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-spot/{id}")
    public ResponseEntity<ParkingSystemResponse<ParkingSpot>> getSpotById(@PathVariable String id) {
        final ParkingSystemResponse<ParkingSpot> response = service.getSpotById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-all-spots")
    public ResponseEntity<ParkingSystemResponse<List<ParkingSpot>>> getAllSpots() {
        final ParkingSystemResponse<List<ParkingSpot>> response = service.getAllSpots();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/add-employee")
    public ResponseEntity<ParkingSystemResponse<Employee>> addEmployee(@RequestBody Employee employee) {
        final ParkingSystemResponse<Employee> response = service.addNewEmployee(employee);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/remove-employee/{id}")
    public ResponseEntity<ParkingSystemResponse<Employee>> removeEmployee(@PathVariable String id) {
        final ParkingSystemResponse<Employee> response = service.removeEmployee(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-employee/{id}")
    public ResponseEntity<ParkingSystemResponse<Employee>> getEmployeeById(@PathVariable String id) {
        final ParkingSystemResponse<Employee> response = service.getEmployeeById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-all-employees")
    public ResponseEntity<ParkingSystemResponse<List<Employee>>> getEmployees() {
        final ParkingSystemResponse<List<Employee>> response = service.getAllEmployees();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/add-assignment")
    public ResponseEntity<ParkingSystemResponse<ParkingSpot>> addAssignment(@RequestBody ParkingSpot parkingSpot) {
        final ParkingSystemResponse<ParkingSpot> response = service.assignSpotToEmployee(
                parkingSpot.getId(), parkingSpot.getEmployeeId(), parkingSpot.getExpiryDate());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/remove-assignment/{id}")
    public ResponseEntity<ParkingSystemResponse<ParkingSpot>> removeAssignment(@PathVariable String id) {
        final ParkingSystemResponse<ParkingSpot> response = service.removeSpotAssignment(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
