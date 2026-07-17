package com.swapnil.appointment_service.mapper;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapnil.appointment_service.dto.AppointmentRequestDTO;
import com.swapnil.appointment_service.dto.AppointmentResponseDTO;
import com.swapnil.appointment_service.entity.Appointment;
import com.swapnil.appointment_service.entity.enums.AppointmentStatus;

public final class AppointmentMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private AppointmentMapper() {
        // Prevent instantiation
    }

    /**
     * Convert Request DTO to Entity
     */
    public static Appointment toEntity(AppointmentRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        return Appointment.builder()
                .patientId(dto.getPatientId())
                .doctorId(dto.getDoctorId())
                .appointmentDate(dto.getAppointmentDate())
                .appointmentTime(dto.getAppointmentTime())
                .consultationReason(dto.getConsultationReason())
                .status(AppointmentStatus.BOOKED)
                .build();
    }

    /**
     * Convert Entity to Response DTO
     */
    public static AppointmentResponseDTO toResponseDTO(Appointment appointment) {

        if (appointment == null) {
            return null;
        }

        return AppointmentResponseDTO.builder()
                .id(appointment.getId())
                .patientId(appointment.getPatientId())
                .doctorId(appointment.getDoctorId())
                .appointmentDate(appointment.getAppointmentDate())
                .appointmentTime(appointment.getAppointmentTime())
                .status(appointment.getStatus())
                .consultationReason(appointment.getConsultationReason())
                .diagnosis(appointment.getDiagnosis())
                .prescription(appointment.getPrescription())
                .visitNotes(appointment.getVisitNotes())
                .createdAt(appointment.getCreatedAt())
                .updatedAt(appointment.getUpdatedAt())
                .build();
    }
}
