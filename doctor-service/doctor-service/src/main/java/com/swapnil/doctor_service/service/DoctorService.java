package com.swapnil.doctor_service.service;

import com.swapnil.doctor_service.dto.DoctorRequestDTO;
import com.swapnil.doctor_service.dto.DoctorResponseDTO;

import java.util.List;

public interface DoctorService {
    DoctorResponseDTO createDoctor(DoctorRequestDTO requestDTO);

    DoctorResponseDTO getDoctorById(Long doctorId);

    List<DoctorResponseDTO> getAllDoctors();

    DoctorResponseDTO updateDoctor(Long doctorId, DoctorRequestDTO requestDTO);

    void deleteDoctor(Long doctorId);
}
