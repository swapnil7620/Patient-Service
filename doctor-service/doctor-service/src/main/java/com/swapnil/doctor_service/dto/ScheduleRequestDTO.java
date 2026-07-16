package com.swapnil.doctor_service.dto;


import com.swapnil.doctor_service.enums.DayOfWeek;
import com.swapnil.doctor_service.enums.ScheduleStatus;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleRequestDTO {

    @NotNull
    private DayOfWeek dayOfWeek;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotNull
    @Min(5)
    private Integer appointmentDuration;

    @NotNull
    private ScheduleStatus status;
}
