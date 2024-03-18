package com.progressoft.model;

public class ParkingLot {
    private String id;
    private Employee employee;
    private boolean available;
    private String expiryDate;

    public ParkingLot(String id) {
        this.id = id;
        available = true;
        employee = null;
        expiryDate = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return this.employee != null ? this.employee.getId() : null;
    }

    public void setEmployeeId(String employeeId) {
        if (this.employee == null) {
            this.employee = new Employee(employeeId);
        } else {
            this.employee.setId(employeeId);
        }
    }

    public String getEmployeeName() {
        return this.employee != null ? this.employee.getName() : null;
    }

    public void setEmployeeName(String employeeName) {
        employee.setName(employeeName);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}


