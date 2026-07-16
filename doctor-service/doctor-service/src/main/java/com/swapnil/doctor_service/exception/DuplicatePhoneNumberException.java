package com.swapnil.doctor_service.exception;

public class DuplicatePhoneNumberException extends  RuntimeException{

    public DuplicatePhoneNumberException(String message) {
        super(message);
    }
}
