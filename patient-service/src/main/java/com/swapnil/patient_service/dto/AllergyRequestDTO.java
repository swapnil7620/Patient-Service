package com.swapnil.patient_service.dto;



import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllergyRequestDTO {

    @NotBlank(message = "Allergy name is required")
    private String allergyName;

    @NotBlank(message = "Severity is required")
    private String severity;

    private LocalDate identifiedDate;
}