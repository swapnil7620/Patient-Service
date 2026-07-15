package com.swapnil.patient_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalHistoryRequestDTO {

    @NotBlank(message = "Medical condition is required")
    private String medicalCondition;

    private String currentMedication;

    private String notes;
}