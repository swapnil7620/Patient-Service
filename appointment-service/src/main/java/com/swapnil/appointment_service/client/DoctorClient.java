package com.swapnil.appointment_service.client;



//import com.swapnil.appointment_service.dto.doctor.DoctorResponseDTO;
import com.swapnil.appointment_service.dto.DoctorResponseDTO;
import com.swapnil.appointment_service.exception.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doctor-service")
public interface DoctorClient {

    @GetMapping("/api/v1/doctors/{id}")
    ApiResponse<DoctorResponseDTO> getDoctorById(@PathVariable Long id);

}
