package com.swapnil.doctor_service.mapper;


import com.swapnil.doctor_service.dto.ScheduleRequestDTO;
import com.swapnil.doctor_service.dto.ScheduleResponseDTO;
import com.swapnil.doctor_service.entity.Schedule;

public class ScheduleMapper {

    private ScheduleMapper() {
    }

    public static Schedule toEntity(ScheduleRequestDTO dto) {

        return Schedule.builder()
                .dayOfWeek(dto.getDayOfWeek())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .appointmentDuration(dto.getAppointmentDuration())
                .status(dto.getStatus())
                .build();
    }

    public static ScheduleResponseDTO toResponseDTO(Schedule schedule) {

        return ScheduleResponseDTO.builder()
                .id(schedule.getId())
                .dayOfWeek(schedule.getDayOfWeek())
                .startTime(schedule.getStartTime())
                .endTime(schedule.getEndTime())
                .appointmentDuration(schedule.getAppointmentDuration())
                .status(schedule.getStatus())
                .build();
    }
}
