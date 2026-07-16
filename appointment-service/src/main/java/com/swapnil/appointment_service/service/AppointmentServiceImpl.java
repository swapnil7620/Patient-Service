package com.swapnil.appointment_service.service;



import com.swapnil.appointment_service.dto.AppointmentRequestDTO;
import com.swapnil.appointment_service.dto.AppointmentResponseDTO;
import com.swapnil.appointment_service.dto.CompleteAppointmentRequestDTO;
import com.swapnil.appointment_service.entity.Appointment;
import com.swapnil.appointment_service.entity.enums.AppointmentStatus;
import com.swapnil.appointment_service.exception.AppointmentNotFoundException;
import com.swapnil.appointment_service.exception.InvalidAppointmentException;
import com.swapnil.appointment_service.mapper.AppointmentMapper;
import com.swapnil.appointment_service.repository.AppointmentRepository;
import com.swapnil.appointment_service.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    @Override
    public AppointmentResponseDTO createAppointment(
            AppointmentRequestDTO requestDTO) {

        // Check whether the doctor already has an appointment
        // at the requested date and time.
        if (appointmentRepository.existsByDoctorIdAndAppointmentDateAndAppointmentTime(
                requestDTO.getDoctorId(),
                requestDTO.getAppointmentDate(),
                requestDTO.getAppointmentTime())) {

            throw new InvalidAppointmentException(
                    "Doctor already has an appointment at the selected date and time.");
        }

        // Prevent duplicate booking by the same patient.
        if (appointmentRepository.existsByPatientIdAndDoctorIdAndAppointmentDateAndAppointmentTime(
                requestDTO.getPatientId(),
                requestDTO.getDoctorId(),
                requestDTO.getAppointmentDate(),
                requestDTO.getAppointmentTime())) {

            throw new InvalidAppointmentException(
                    "You have already booked this appointment.");
        }

        Appointment appointment = AppointmentMapper.toEntity(requestDTO);
        appointment.setStatus(AppointmentStatus.BOOKED);
        Appointment savedAppointment =
                appointmentRepository.save(appointment);

        return AppointmentMapper.toResponseDTO(savedAppointment);
    }

    @Override
    public List<AppointmentResponseDTO> getAllAppointments() {

        return appointmentRepository.findAll()
                .stream()
                .map(AppointmentMapper::toResponseDTO)
                .toList();
    }

    @Override
    public AppointmentResponseDTO getAppointmentById(Long appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment not found with ID : " + appointmentId));

        return AppointmentMapper.toResponseDTO(appointment);
    }

    @Override
    public AppointmentResponseDTO updateAppointment(
            Long appointmentId,
            AppointmentRequestDTO requestDTO) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment not found with ID : " + appointmentId));

        /*
         * Check if another appointment already exists
         * for the same doctor, date and time.
         */
        boolean doctorAlreadyBooked =
                appointmentRepository.existsByDoctorIdAndAppointmentDateAndAppointmentTime(
                        requestDTO.getDoctorId(),
                        requestDTO.getAppointmentDate(),
                        requestDTO.getAppointmentTime());

        if (doctorAlreadyBooked &&
                (!appointment.getDoctorId().equals(requestDTO.getDoctorId())
                        || !appointment.getAppointmentDate().equals(requestDTO.getAppointmentDate())
                        || !appointment.getAppointmentTime().equals(requestDTO.getAppointmentTime()))) {

            throw new InvalidAppointmentException(
                    "Doctor already has an appointment at the selected date and time.");
        }

        appointment.setPatientId(requestDTO.getPatientId());
        appointment.setDoctorId(requestDTO.getDoctorId());
        appointment.setAppointmentDate(requestDTO.getAppointmentDate());
        appointment.setAppointmentTime(requestDTO.getAppointmentTime());
        appointment.setConsultationReason(requestDTO.getConsultationReason());

        Appointment updatedAppointment =
                appointmentRepository.save(appointment);

        return AppointmentMapper.toResponseDTO(updatedAppointment);
    }

    @Override
    public void deleteAppointment(Long appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment not found with ID : " + appointmentId));

        appointmentRepository.delete(appointment);
    }

    @Override
    public AppointmentResponseDTO cancelAppointment(Long appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment not found with ID : " + appointmentId));

        switch (appointment.getStatus()) {

            case BOOKED, CONFIRMED -> {
                appointment.setStatus(AppointmentStatus.CANCELLED);

                Appointment cancelledAppointment =
                        appointmentRepository.save(appointment);

                return AppointmentMapper.toResponseDTO(cancelledAppointment);
            }

            case CANCELLED -> throw new InvalidAppointmentException(
                    "Appointment is already cancelled.");

            case IN_PROGRESS -> throw new InvalidAppointmentException(
                    "Appointment is in progress and cannot be cancelled.");

            case COMPLETED -> throw new InvalidAppointmentException(
                    "Completed appointment cannot be cancelled.");

            default -> throw new InvalidAppointmentException(
                    "Invalid appointment status.");
        }
    }


    // To confirm Appointment
    @Override
    public AppointmentResponseDTO confirmAppointment(Long appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment not found with ID : " + appointmentId));

        switch (appointment.getStatus()) {

            case BOOKED -> {
                appointment.setStatus(AppointmentStatus.CONFIRMED);

                Appointment confirmedAppointment =
                        appointmentRepository.save(appointment);

                return AppointmentMapper.toResponseDTO(confirmedAppointment);
            }

            case CONFIRMED -> throw new InvalidAppointmentException(
                    "Appointment is already confirmed.");

            case IN_PROGRESS -> throw new InvalidAppointmentException(
                    "Appointment is already in progress.");

            case COMPLETED -> throw new InvalidAppointmentException(
                    "Completed appointment cannot be confirmed.");

            case CANCELLED -> throw new InvalidAppointmentException(
                    "Cancelled appointment cannot be confirmed.");

            default -> throw new InvalidAppointmentException(
                    "Invalid appointment status.");
        }
    }

    @Override
    public AppointmentResponseDTO startConsultation(Long appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment not found with ID : " + appointmentId));

        switch (appointment.getStatus()) {

            case CONFIRMED -> {
                appointment.setStatus(AppointmentStatus.IN_PROGRESS);

                Appointment updatedAppointment =
                        appointmentRepository.save(appointment);

                return AppointmentMapper.toResponseDTO(updatedAppointment);
            }

            case BOOKED -> throw new InvalidAppointmentException(
                    "Appointment must be confirmed before starting consultation.");

            case IN_PROGRESS -> throw new InvalidAppointmentException(
                    "Consultation is already in progress.");

            case COMPLETED -> throw new InvalidAppointmentException(
                    "Consultation has already been completed.");

            case CANCELLED -> throw new InvalidAppointmentException(
                    "Cancelled appointment cannot be started.");

            default -> throw new InvalidAppointmentException(
                    "Invalid appointment status.");
        }
    }

    @Override
    public AppointmentResponseDTO completeAppointment(
            Long appointmentId,
            CompleteAppointmentRequestDTO requestDTO) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment not found with ID : " + appointmentId));

        switch (appointment.getStatus()) {

            case IN_PROGRESS -> {

                appointment.setDiagnosis(requestDTO.getDiagnosis());
                appointment.setPrescription(requestDTO.getPrescription());
                appointment.setVisitNotes(requestDTO.getVisitNotes());

                appointment.setStatus(AppointmentStatus.COMPLETED);

                Appointment completedAppointment =
                        appointmentRepository.save(appointment);

                return AppointmentMapper.toResponseDTO(completedAppointment);
            }

            case BOOKED -> throw new InvalidAppointmentException(
                    "Consultation has not started yet.");

            case CONFIRMED -> throw new InvalidAppointmentException(
                    "Start consultation before completing it.");

            case COMPLETED -> throw new InvalidAppointmentException(
                    "Appointment is already completed.");

            case CANCELLED -> throw new InvalidAppointmentException(
                    "Cancelled appointment cannot be completed.");

            default -> throw new InvalidAppointmentException(
                    "Invalid appointment status.");
        }
    }
}
