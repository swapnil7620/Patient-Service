package com.swapnil.doctor_service.entity;



import com.swapnil.doctor_service.enums.DayOfWeek;
import com.swapnil.doctor_service.enums.ScheduleStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Day of week is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false, length = 15)
    private DayOfWeek dayOfWeek;

    @NotNull(message = "Start time is required")
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @NotNull(message = "Appointment duration is required")
    @Min(value = 5, message = "Appointment duration must be at least 5 minutes")
    @Max(value = 180, message = "Appointment duration cannot exceed 180 minutes")
    @Column(name = "appointment_duration", nullable = false)
    private Integer appointmentDuration;

    @NotNull(message = "Schedule status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ScheduleStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
}