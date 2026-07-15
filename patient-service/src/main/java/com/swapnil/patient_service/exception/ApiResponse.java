package com.swapnil.patient_service.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiResponse<T> {

    private int status;

    private String message;

    private T data;

    private LocalDateTime timestamp;
}