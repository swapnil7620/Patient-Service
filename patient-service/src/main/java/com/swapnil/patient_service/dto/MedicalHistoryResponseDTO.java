package com.swapnil.patient_service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalHistoryResponseDTO {

    private Long id;

    private String medicalCondition;

    private String currentMedication;

    private String notes;

    private LocalDateTime updatedAt;
}