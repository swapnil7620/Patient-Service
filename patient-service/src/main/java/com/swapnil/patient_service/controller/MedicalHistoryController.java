package com.swapnil.patient_service.controller;



import com.swapnil.patient_service.dto.MedicalHistoryRequestDTO;
import com.swapnil.patient_service.dto.MedicalHistoryResponseDTO;
import com.swapnil.patient_service.exception.ApiResponse;
import com.swapnil.patient_service.service.MedicalHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/patients/{patientId}/medical-history")
@RequiredArgsConstructor
public class MedicalHistoryController {

    private final MedicalHistoryService medicalHistoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<MedicalHistoryResponseDTO>> addMedicalHistory(
            @PathVariable Long patientId,
            @Valid @RequestBody MedicalHistoryRequestDTO requestDTO) {

        MedicalHistoryResponseDTO responseDTO =
                medicalHistoryService.addMedicalHistory(patientId, requestDTO);

        ApiResponse<MedicalHistoryResponseDTO> response =
                ApiResponse.<MedicalHistoryResponseDTO>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Medical history added successfully.")
                        .data(responseDTO)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<MedicalHistoryResponseDTO>> getMedicalHistory(
            @PathVariable Long patientId) {

        MedicalHistoryResponseDTO responseDTO =
                medicalHistoryService.getMedicalHistoryByPatientId(patientId);

        ApiResponse<MedicalHistoryResponseDTO> response =
                ApiResponse.<MedicalHistoryResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Medical history retrieved successfully.")
                        .data(responseDTO)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<MedicalHistoryResponseDTO>> updateMedicalHistory(
            @PathVariable Long patientId,
            @Valid @RequestBody MedicalHistoryRequestDTO requestDTO) {

        MedicalHistoryResponseDTO responseDTO =
                medicalHistoryService.updateMedicalHistory(patientId, requestDTO);

        ApiResponse<MedicalHistoryResponseDTO> response =
                ApiResponse.<MedicalHistoryResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Medical history updated successfully.")
                        .data(responseDTO)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteMedicalHistory(
            @PathVariable Long patientId) {

        medicalHistoryService.deleteMedicalHistory(patientId);

        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .status(HttpStatus.OK.value())
                        .message("Medical history deleted successfully.")
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }
}