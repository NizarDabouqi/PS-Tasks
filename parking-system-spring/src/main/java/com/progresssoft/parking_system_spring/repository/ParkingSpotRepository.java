package com.progresssoft.parking_system_spring.repository;

import com.progresssoft.parking_system_spring.model.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, String> {

    List<ParkingSpot> findByEmployeeId(String employeeId);
}
