package com.swapnil.doctor_service.exception;



import com.swapnil.doctor_service.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ScheduleConflictException.class)
    public ResponseEntity<ApiResponse<Object>> handleScheduleConflictException(
            ScheduleConflictException ex) {

        ApiResponse<Object> response = ApiResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(DuplicateScheduleException.class)
    public ResponseEntity<ApiResponse<Object>> handleDuplicateScheduleException(
            DuplicateScheduleException ex) {

        ApiResponse<Object> response = ApiResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(InvalidScheduleException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidScheduleException(
            InvalidScheduleException ex) {

        ApiResponse<Object> response = ApiResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleDoctorNotFoundException(
            DoctorNotFoundException ex) {

        ApiResponse<Object> response = ApiResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleScheduleNotFoundException(
            ScheduleNotFoundException ex) {

        ApiResponse<Object> response = ApiResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ApiResponse<Object>> handleDuplicateEmailException(
            DuplicateEmailException ex) {

        ApiResponse<Object> response = ApiResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(DuplicatePhoneNumberException.class)
    public ResponseEntity<ApiResponse<Object>> handleDuplicatePhoneNumberException(
            DuplicatePhoneNumberException ex) {

        ApiResponse<Object> response = ApiResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage()));

        ApiResponse<Object> response = ApiResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation Failed")
                .data(errors)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGlobalException(
            Exception ex) {

        ApiResponse<Object> response = ApiResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
