package com.swapnil.appointment_service.service;



import com.swapnil.appointment_service.dto.AppointmentRequestDTO;
import com.swapnil.appointment_service.dto.AppointmentResponseDTO;
import com.swapnil.appointment_service.dto.CompleteAppointmentRequestDTO;

import java.util.List;

public interface AppointmentService {

    /**
     * Book a new appointment.
     */
    AppointmentResponseDTO createAppointment(
            AppointmentRequestDTO requestDTO
    );

    /**
     * Retrieve all appointments.
     */
    List<AppointmentResponseDTO> getAllAppointments();

    /**
     * Retrieve an appointment by ID.
     */
    AppointmentResponseDTO getAppointmentById(
            Long appointmentId
    );

    /**
     * Update an existing appointment.
     */
    AppointmentResponseDTO updateAppointment(
            Long appointmentId,
            AppointmentRequestDTO requestDTO
    );

    /**
     * Delete an appointment.
     */
    void deleteAppointment(
            Long appointmentId
    );


    AppointmentResponseDTO cancelAppointment(Long appointmentId);
    AppointmentResponseDTO confirmAppointment(Long appointmentId);
    AppointmentResponseDTO startConsultation(Long appointmentId);
    AppointmentResponseDTO completeAppointment(
            Long appointmentId,
            CompleteAppointmentRequestDTO requestDTO);

}
