package com.progresssoft.parking_system_spring.exceptions;

public class ParkingSystemException extends RuntimeException {
    public ParkingSystemException(String message, Throwable exception) {
        super(message, exception);
    }

    public ParkingSystemException(String message) {
        super(message);
    }

}
