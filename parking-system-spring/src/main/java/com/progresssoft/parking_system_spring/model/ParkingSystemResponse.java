package com.progresssoft.parking_system_spring.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ParkingSystemResponse<T> {
    private HttpStatus statusCode;

    private String message;

    private T body;
}
