package com.progresssoft.parking_system_spring.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpot {

    @Id
    private String id;

    private String employeeId;

    private LocalDate expiryDate;

    public boolean isAvailable() {
        return Optional.ofNullable(this.employeeId).isEmpty();
    }

}
