package com.swapnil.appointment_service.client;

import com.swapnil.appointment_service.dto.PatientResponseDTO;
import com.swapnil.appointment_service.exception.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service")
public interface PatientClient {

    @GetMapping("/api/v1/patients/{id}")
    ApiResponse<PatientResponseDTO> getPatientById(@PathVariable Long id);
}