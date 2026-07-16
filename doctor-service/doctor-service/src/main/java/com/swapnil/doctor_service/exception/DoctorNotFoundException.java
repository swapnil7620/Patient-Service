package com.swapnil.doctor_service.exception;

public class DoctorNotFoundException extends  RuntimeException{

    public DoctorNotFoundException(String message) {
        super(message);
    }
}
