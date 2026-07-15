package com.swapnil.patient_service.controller;



import com.swapnil.patient_service.dto.AllergyRequestDTO;
import com.swapnil.patient_service.dto.AllergyResponseDTO;
import com.swapnil.patient_service.exception.ApiResponse;
import com.swapnil.patient_service.service.AllergyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/v1/patients/{patientId}/allergies")
@RequiredArgsConstructor
public class AllergyController {


    private final AllergyService allergyService;


    // Add Allergy
    @PostMapping
    public ResponseEntity<ApiResponse<AllergyResponseDTO>> addAllergy(
            @PathVariable Long patientId,
            @Valid @RequestBody AllergyRequestDTO requestDTO) {


        AllergyResponseDTO responseDTO =
                allergyService.addAllergy(patientId, requestDTO);


        ApiResponse<AllergyResponseDTO> response =
                ApiResponse.<AllergyResponseDTO>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Allergy added successfully.")
                        .data(responseDTO)
                        .timestamp(LocalDateTime.now())
                        .build();


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }



    // Get All Allergies Of Patient
    @GetMapping
    public ResponseEntity<ApiResponse<List<AllergyResponseDTO>>> getAllergies(
            @PathVariable Long patientId) {


        List<AllergyResponseDTO> allergies =
                allergyService.getAllergiesByPatientId(patientId);


        ApiResponse<List<AllergyResponseDTO>> response =
                ApiResponse.<List<AllergyResponseDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Allergies retrieved successfully.")
                        .data(allergies)
                        .timestamp(LocalDateTime.now())
                        .build();


        return ResponseEntity.ok(response);
    }



    // Get Specific Allergy
    @GetMapping("/{allergyId}")
    public ResponseEntity<ApiResponse<AllergyResponseDTO>> getAllergyById(
            @PathVariable Long patientId,
            @PathVariable Long allergyId) {


        AllergyResponseDTO allergy =
                allergyService.getAllergyById(patientId, allergyId);


        ApiResponse<AllergyResponseDTO> response =
                ApiResponse.<AllergyResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Allergy retrieved successfully.")
                        .data(allergy)
                        .timestamp(LocalDateTime.now())
                        .build();


        return ResponseEntity.ok(response);
    }




    // Update Allergy
    @PutMapping("/{allergyId}")
    public ResponseEntity<ApiResponse<AllergyResponseDTO>> updateAllergy(
            @PathVariable Long patientId,
            @PathVariable Long allergyId,
            @Valid @RequestBody AllergyRequestDTO requestDTO) {


        AllergyResponseDTO updated =
                allergyService.updateAllergy(
                        patientId,
                        allergyId,
                        requestDTO
                );


        ApiResponse<AllergyResponseDTO> response =
                ApiResponse.<AllergyResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Allergy updated successfully.")
                        .data(updated)
                        .timestamp(LocalDateTime.now())
                        .build();


        return ResponseEntity.ok(response);
    }




    // Delete Allergy
    @DeleteMapping("/{allergyId}")
    public ResponseEntity<ApiResponse<Void>> deleteAllergy(
            @PathVariable Long patientId,
            @PathVariable Long allergyId) {


        allergyService.deleteAllergy(
                patientId,
                allergyId
        );


        ApiResponse<Void> response =
                ApiResponse.<Void>builder()
                        .status(HttpStatus.OK.value())
                        .message("Allergy deleted successfully.")
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build();


        return ResponseEntity.ok(response);
    }

}