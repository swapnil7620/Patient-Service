package com.swapnil.doctor_service.exception;



public class DuplicateScheduleException extends RuntimeException {

    public DuplicateScheduleException(String message) {
        super(message);
    }
}