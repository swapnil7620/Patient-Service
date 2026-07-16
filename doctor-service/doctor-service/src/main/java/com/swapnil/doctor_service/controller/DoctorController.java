package com.swapnil.doctor_service.controller;



import com.swapnil.doctor_service.dto.DoctorRequestDTO;
import com.swapnil.doctor_service.dto.DoctorResponseDTO;
import com.swapnil.doctor_service.service.DoctorService;
import com.swapnil.doctor_service.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<ApiResponse<DoctorResponseDTO>> createDoctor(
            @Valid @RequestBody DoctorRequestDTO requestDTO) {

        DoctorResponseDTO doctor = doctorService.createDoctor(requestDTO);

        ApiResponse<DoctorResponseDTO> response = ApiResponse.<DoctorResponseDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Doctor created successfully.")
                .data(doctor)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DoctorResponseDTO>>> getAllDoctors() {

        List<DoctorResponseDTO> doctors = doctorService.getAllDoctors();

        ApiResponse<List<DoctorResponseDTO>> response = ApiResponse.<List<DoctorResponseDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("Doctors retrieved successfully.")
                .data(doctors)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<ApiResponse<DoctorResponseDTO>> getDoctorById(
            @PathVariable Long doctorId) {

        DoctorResponseDTO doctor = doctorService.getDoctorById(doctorId);

        ApiResponse<DoctorResponseDTO> response = ApiResponse.<DoctorResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Doctor retrieved successfully.")
                .data(doctor)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<ApiResponse<DoctorResponseDTO>> updateDoctor(
            @PathVariable Long doctorId,
            @Valid @RequestBody DoctorRequestDTO requestDTO) {

        DoctorResponseDTO doctor =
                doctorService.updateDoctor(doctorId, requestDTO);

        ApiResponse<DoctorResponseDTO> response = ApiResponse.<DoctorResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Doctor updated successfully.")
                .data(doctor)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<ApiResponse<String>> deleteDoctor(
            @PathVariable Long doctorId) {

        doctorService.deleteDoctor(doctorId);

        ApiResponse<String> response = ApiResponse.<String>builder()
                .status(HttpStatus.OK.value())
                .message("Doctor deleted successfully.")
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}