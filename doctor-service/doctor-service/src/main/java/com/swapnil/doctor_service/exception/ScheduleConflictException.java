package com.swapnil.doctor_service.exception;


public class ScheduleConflictException extends RuntimeException {

    public ScheduleConflictException(String message) {
        super(message);
    }
}
