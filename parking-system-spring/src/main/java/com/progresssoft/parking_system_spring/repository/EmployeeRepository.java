package com.progresssoft.parking_system_spring.repository;

import com.progresssoft.parking_system_spring.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
