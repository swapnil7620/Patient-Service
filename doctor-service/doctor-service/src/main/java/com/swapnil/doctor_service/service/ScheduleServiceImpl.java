package com.swapnil.doctor_service.service;


import com.swapnil.doctor_service.dto.ScheduleRequestDTO;
import com.swapnil.doctor_service.dto.ScheduleResponseDTO;
import com.swapnil.doctor_service.entity.Doctor;
import com.swapnil.doctor_service.entity.Schedule;
import com.swapnil.doctor_service.exception.*;
import com.swapnil.doctor_service.mapper.ScheduleMapper;
import com.swapnil.doctor_service.repository.DoctorRepository;
//import com.swapnil.doctor_service.repository.ScheduleRepository;
import com.swapnil.doctor_service.repository.ScheduleRepository;
//import com.swapnil.doctor_service.repository.scheduleRepository;
import com.swapnil.doctor_service.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final DoctorRepository doctorRepository;
    private final ScheduleRepository scheduleRepository;

    private void validateScheduleTime(ScheduleRequestDTO requestDTO) {

        if (!requestDTO.getStartTime().isBefore(requestDTO.getEndTime())) {
            throw new InvalidScheduleException(
                    "Start time must be before end time.");
        }
    }

    private void validateOverlappingSchedule(Long doctorId,
                                             ScheduleRequestDTO requestDTO) {

        boolean overlap = scheduleRepository.existsOverlappingSchedule(
                doctorId,
                requestDTO.getDayOfWeek(),
                requestDTO.getStartTime(),
                requestDTO.getEndTime());

        if (overlap) {
            throw new ScheduleConflictException(
                    "Doctor already has another schedule during this time.");
        }
    }


    private void validateDuplicateSchedule(Long doctorId,
                                           ScheduleRequestDTO requestDTO) {

        boolean exists = scheduleRepository
                .existsByDoctorIdAndDayOfWeekAndStartTimeAndEndTime(
                        doctorId,
                        requestDTO.getDayOfWeek(),
                        requestDTO.getStartTime(),
                        requestDTO.getEndTime());

        if (exists) {
            throw new DuplicateScheduleException(
                    "Schedule already exists for this doctor.");
        }
    }

    @Override
    public ScheduleResponseDTO createSchedule(Long doctorId,
                                              ScheduleRequestDTO requestDTO) {



        validateScheduleTime(requestDTO);
        validateDuplicateSchedule(doctorId, requestDTO);
        validateOverlappingSchedule(doctorId, requestDTO);


        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor not found with ID : " + doctorId));

        Schedule schedule = ScheduleMapper.toEntity(requestDTO);
        schedule.setDoctor(doctor);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleMapper.toResponseDTO(savedSchedule);
    }

    @Override
    public List<ScheduleResponseDTO> getSchedulesByDoctor(Long doctorId) {

        doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor not found with ID : " + doctorId));

        return scheduleRepository.findByDoctorId(doctorId)
                .stream()
                .map(ScheduleMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ScheduleResponseDTO getScheduleById(Long doctorId,
                                               Long scheduleId) {

        doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor not found with ID : " + doctorId));

        Schedule schedule = scheduleRepository.findByIdAndDoctorId(scheduleId, doctorId)
                .orElseThrow(() ->
                        new ScheduleNotFoundException(
                                "Schedule not found for Doctor ID : " + doctorId));

        return ScheduleMapper.toResponseDTO(schedule);
    }

    @Override
    public ScheduleResponseDTO updateSchedule(Long doctorId,
                                              Long scheduleId,
                                              ScheduleRequestDTO requestDTO) {

        validateScheduleTime(requestDTO);

        doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor not found with ID : " + doctorId));

        Schedule schedule = scheduleRepository.findByIdAndDoctorId(scheduleId, doctorId)
                .orElseThrow(() ->
                        new ScheduleNotFoundException(
                                "Schedule not found for Doctor ID : " + doctorId));

        schedule.setDayOfWeek(requestDTO.getDayOfWeek());
        schedule.setStartTime(requestDTO.getStartTime());
        schedule.setEndTime(requestDTO.getEndTime());
        schedule.setAppointmentDuration(requestDTO.getAppointmentDuration());
        schedule.setStatus(requestDTO.getStatus());

        Schedule updatedSchedule = scheduleRepository.save(schedule);

        return ScheduleMapper.toResponseDTO(updatedSchedule);
    }

    @Override
    public void deleteSchedule(Long doctorId,
                               Long scheduleId) {

        doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor not found with ID : " + doctorId));

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() ->
                        new ScheduleNotFoundException(
                                "Schedule not found with ID : " + scheduleId));

        if (!schedule.getDoctor().getId().equals(doctorId)) {
            throw new ScheduleNotFoundException(
                    "Schedule does not belong to Doctor ID : " + doctorId);
        }

        scheduleRepository.delete(schedule);
    }
}
