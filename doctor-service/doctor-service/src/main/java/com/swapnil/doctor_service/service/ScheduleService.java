package com.swapnil.doctor_service.service;

import com.swapnil.doctor_service.dto.ScheduleRequestDTO;
import com.swapnil.doctor_service.dto.ScheduleResponseDTO;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDTO createSchedule(Long doctorId, ScheduleRequestDTO requestDTO);

    List<ScheduleResponseDTO> getSchedulesByDoctor(Long doctorId);

    ScheduleResponseDTO getScheduleById(Long doctorId, Long scheduleId);

    ScheduleResponseDTO updateSchedule(Long doctorId,
                                       Long scheduleId,
                                       ScheduleRequestDTO requestDTO);

    void deleteSchedule(Long doctorId, Long scheduleId);
}
