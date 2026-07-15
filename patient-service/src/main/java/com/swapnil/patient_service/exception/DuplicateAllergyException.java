package com.swapnil.patient_service.exception;

public class DuplicateAllergyException extends RuntimeException {

    public DuplicateAllergyException(String message) {
        super(message);
    }
}