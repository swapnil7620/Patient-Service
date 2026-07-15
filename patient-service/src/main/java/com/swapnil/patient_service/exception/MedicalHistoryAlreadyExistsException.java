package com.swapnil.patient_service.exception;

public class MedicalHistoryAlreadyExistsException extends RuntimeException {

    public MedicalHistoryAlreadyExistsException(String message) {
        super(message);
    }
}
