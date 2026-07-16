package com.swapnil.appointment_service.dto;



import com.swapnil.appointment_service.entity.enums.AppointmentStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentResponseDTO {

    private Long id;

    private Long patientId;

    private Long doctorId;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private AppointmentStatus status;

    private String consultationReason;

    private String diagnosis;

    private String prescription;

    private String visitNotes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}