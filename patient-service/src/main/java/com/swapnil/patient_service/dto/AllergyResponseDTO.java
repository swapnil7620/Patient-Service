package com.swapnil.patient_service.dto;



import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllergyResponseDTO {

    private Long id;

    private String allergyName;

    private String severity;

    private LocalDate identifiedDate;
}