package com.swapnil.doctor_service.controller;



import com.swapnil.doctor_service.dto.ScheduleRequestDTO;
import com.swapnil.doctor_service.dto.ScheduleResponseDTO;
import com.swapnil.doctor_service.service.ScheduleService;
import com.swapnil.doctor_service.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors/{doctorId}/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ApiResponse<ScheduleResponseDTO>> createSchedule(
            @PathVariable Long doctorId,
            @Valid @RequestBody ScheduleRequestDTO requestDTO) {

        ScheduleResponseDTO schedule =
                scheduleService.createSchedule(doctorId, requestDTO);

        ApiResponse<ScheduleResponseDTO> response = ApiResponse.<ScheduleResponseDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Schedule created successfully.")
                .data(schedule)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ScheduleResponseDTO>>> getSchedules(
            @PathVariable Long doctorId) {

        List<ScheduleResponseDTO> schedules =
                scheduleService.getSchedulesByDoctor(doctorId);

        ApiResponse<List<ScheduleResponseDTO>> response = ApiResponse.<List<ScheduleResponseDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("Schedules retrieved successfully.")
                .data(schedules)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ApiResponse<ScheduleResponseDTO>> getScheduleById(
            @PathVariable Long doctorId,
            @PathVariable Long scheduleId) {

        ScheduleResponseDTO schedule =
                scheduleService.getScheduleById(doctorId, scheduleId);

        ApiResponse<ScheduleResponseDTO> response = ApiResponse.<ScheduleResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Schedule retrieved successfully.")
                .data(schedule)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ApiResponse<ScheduleResponseDTO>> updateSchedule(
            @PathVariable Long doctorId,
            @PathVariable Long scheduleId,
            @Valid @RequestBody ScheduleRequestDTO requestDTO) {

        ScheduleResponseDTO schedule =
                scheduleService.updateSchedule(doctorId, scheduleId, requestDTO);

        ApiResponse<ScheduleResponseDTO> response = ApiResponse.<ScheduleResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Schedule updated successfully.")
                .data(schedule)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ApiResponse<String>> deleteSchedule(
            @PathVariable Long doctorId,
            @PathVariable Long scheduleId) {

        scheduleService.deleteSchedule(doctorId, scheduleId);

        ApiResponse<String> response = ApiResponse.<String>builder()
                .status(HttpStatus.OK.value())
                .message("Schedule deleted successfully.")
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}
