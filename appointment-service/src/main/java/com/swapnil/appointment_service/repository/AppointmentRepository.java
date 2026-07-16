package com.swapnil.appointment_service.repository;



import com.swapnil.appointment_service.entity.Appointment;
import com.swapnil.appointment_service.entity.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    /**
     * Get all appointments of a patient.
     */
    List<Appointment> findByPatientId(Long patientId);

    /**
     * Get all appointments of a doctor.
     */
    List<Appointment> findByDoctorId(Long doctorId);

    /**
     * Get appointments by status.
     */
    List<Appointment> findByStatus(AppointmentStatus status);

    /**
     * Get appointments of a doctor on a specific date.
     */
    List<Appointment> findByDoctorIdAndAppointmentDate(
            Long doctorId,
            LocalDate appointmentDate
    );

    /**
     * Check whether a doctor already has an appointment
     * at the given date and time.
     */
    boolean existsByDoctorIdAndAppointmentDateAndAppointmentTime(
            Long doctorId,
            LocalDate appointmentDate,
            LocalTime appointmentTime
    );

    /**
     * Check whether a patient already booked
     * the same doctor at the same date and time.
     */
    boolean existsByPatientIdAndDoctorIdAndAppointmentDateAndAppointmentTime(
            Long patientId,
            Long doctorId,
            LocalDate appointmentDate,
            LocalTime appointmentTime
    );

}