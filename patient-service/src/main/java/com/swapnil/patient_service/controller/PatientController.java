package com.swapnil.patient_service.controller;

import org.springframework.web.bind.annotation.RestController;


import com.swapnil.patient_service.dto.PatientRequestDTO;
import com.swapnil.patient_service.dto.PatientResponseDTO;
import com.swapnil.patient_service.exception.ApiResponse;
import com.swapnil.patient_service.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<ApiResponse<PatientResponseDTO>> createPatient(
            @Valid @RequestBody PatientRequestDTO requestDTO) {

        PatientResponseDTO responseDTO = patientService.createPatient(requestDTO);

        ApiResponse<PatientResponseDTO> response = ApiResponse.<PatientResponseDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Patient created successfully.")
                .data(responseDTO)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientResponseDTO>> getPatientById(
            @PathVariable Long id) {

        PatientResponseDTO responseDTO = patientService.getPatientById(id);

        ApiResponse<PatientResponseDTO> response = ApiResponse.<PatientResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Patient retrieved successfully.")
                .data(responseDTO)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PatientResponseDTO>>> getAllPatients() {

        List<PatientResponseDTO> patients = patientService.getAllPatients();

        ApiResponse<List<PatientResponseDTO>> response = ApiResponse.<List<PatientResponseDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("Patients retrieved successfully.")
                .data(patients)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientResponseDTO>> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody PatientRequestDTO requestDTO) {

        PatientResponseDTO responseDTO = patientService.updatePatient(id, requestDTO);

        ApiResponse<PatientResponseDTO> response = ApiResponse.<PatientResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Patient updated successfully.")
                .data(responseDTO)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePatient(
            @PathVariable Long id) {

        patientService.deletePatient(id);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .message("Patient deleted successfully.")
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}
