package com.swapnil.appointment_service.controller;


import com.swapnil.appointment_service.dto.AppointmentRequestDTO;
import com.swapnil.appointment_service.dto.AppointmentResponseDTO;
import com.swapnil.appointment_service.dto.CompleteAppointmentRequestDTO;
import com.swapnil.appointment_service.exception.ApiResponse;
import com.swapnil.appointment_service.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;


    @PostMapping
    public ResponseEntity<ApiResponse<AppointmentResponseDTO>> createAppointment(
            @Valid @RequestBody AppointmentRequestDTO requestDTO) {

        AppointmentResponseDTO responseDTO =
                appointmentService.createAppointment(requestDTO);

        ApiResponse<AppointmentResponseDTO> response =
                ApiResponse.<AppointmentResponseDTO>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Appointment booked successfully.")
                        .data(responseDTO)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<AppointmentResponseDTO>>> getAllAppointments() {

        List<AppointmentResponseDTO> appointments =
                appointmentService.getAllAppointments();

        ApiResponse<List<AppointmentResponseDTO>> response =
                ApiResponse.<List<AppointmentResponseDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Appointments fetched successfully.")
                        .data(appointments)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{appointmentId}")
    public ResponseEntity<ApiResponse<AppointmentResponseDTO>> getAppointmentById(
            @PathVariable Long appointmentId) {

        AppointmentResponseDTO responseDTO =
                appointmentService.getAppointmentById(appointmentId);

        ApiResponse<AppointmentResponseDTO> response =
                ApiResponse.<AppointmentResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Appointment fetched successfully.")
                        .data(responseDTO)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<ApiResponse<AppointmentResponseDTO>> updateAppointment(
            @PathVariable Long appointmentId,
            @Valid @RequestBody AppointmentRequestDTO requestDTO) {

        AppointmentResponseDTO responseDTO =
                appointmentService.updateAppointment(appointmentId, requestDTO);

        ApiResponse<AppointmentResponseDTO> response =
                ApiResponse.<AppointmentResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Appointment updated successfully.")
                        .data(responseDTO)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<ApiResponse<Void>> deleteAppointment(
            @PathVariable Long appointmentId) {

        appointmentService.deleteAppointment(appointmentId);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .message("Appointment deleted successfully.")
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    // To cancel appointment
    @PatchMapping("/{appointmentId}/cancel")
    public ResponseEntity<ApiResponse<AppointmentResponseDTO>> cancelAppointment(
            @PathVariable Long appointmentId) {

        AppointmentResponseDTO responseDTO =
                appointmentService.cancelAppointment(appointmentId);

        ApiResponse<AppointmentResponseDTO> response =
                ApiResponse.<AppointmentResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Appointment cancelled successfully.")
                        .data(responseDTO)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }


    // Appointment confirm
    @PatchMapping("/{appointmentId}/confirm")
    public ResponseEntity<ApiResponse<AppointmentResponseDTO>> confirmAppointment(
            @PathVariable Long appointmentId) {

        AppointmentResponseDTO responseDTO =
                appointmentService.confirmAppointment(appointmentId);

        ApiResponse<AppointmentResponseDTO> response =
                ApiResponse.<AppointmentResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Appointment confirmed successfully.")
                        .data(responseDTO)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{appointmentId}/start")
    public ResponseEntity<ApiResponse<AppointmentResponseDTO>> startConsultation(
            @PathVariable Long appointmentId) {

        AppointmentResponseDTO responseDTO =
                appointmentService.startConsultation(appointmentId);

        ApiResponse<AppointmentResponseDTO> response =
                ApiResponse.<AppointmentResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Consultation started successfully.")
                        .data(responseDTO)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{appointmentId}/complete")
    public ResponseEntity<ApiResponse<AppointmentResponseDTO>> completeAppointment(
            @PathVariable Long appointmentId,
            @Valid @RequestBody CompleteAppointmentRequestDTO requestDTO) {

        AppointmentResponseDTO responseDTO =
                appointmentService.completeAppointment(
                        appointmentId,
                        requestDTO);

        ApiResponse<AppointmentResponseDTO> response =
                ApiResponse.<AppointmentResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Appointment completed successfully.")
                        .data(responseDTO)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

}