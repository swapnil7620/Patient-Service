package com.swapnil.doctor_service.dto;


import com.swapnil.doctor_service.enums.DayOfWeek;
import com.swapnil.doctor_service.enums.ScheduleStatus;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleResponseDTO {

    private Long id;

    private DayOfWeek dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private Integer appointmentDuration;

    private ScheduleStatus status;
}