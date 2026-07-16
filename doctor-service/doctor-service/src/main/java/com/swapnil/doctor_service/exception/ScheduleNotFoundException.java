package com.swapnil.doctor_service.exception;

public class ScheduleNotFoundException extends RuntimeException {

    public ScheduleNotFoundException(String message) {
        super(message);
    }

}
