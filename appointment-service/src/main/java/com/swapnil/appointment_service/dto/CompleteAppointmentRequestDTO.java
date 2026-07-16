package com.swapnil.appointment_service.dto;



import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompleteAppointmentRequestDTO {

    @NotBlank(message = "Diagnosis is required.")
    private String diagnosis;

    @NotBlank(message = "Prescription is required.")
    private String prescription;

    @NotBlank(message = "Visit notes are required.")
    private String visitNotes;
}