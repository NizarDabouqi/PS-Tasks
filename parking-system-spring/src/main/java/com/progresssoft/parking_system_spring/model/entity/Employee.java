package com.progresssoft.parking_system_spring.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Employee {

    @Id
    @Getter
    private String id;
    private String name;

}
