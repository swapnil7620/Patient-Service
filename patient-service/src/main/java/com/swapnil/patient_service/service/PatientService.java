package com.swapnil.patient_service.service;

import com.swapnil.patient_service.dto.PatientRequestDTO;
import com.swapnil.patient_service.dto.PatientResponseDTO;

import java.util.List;

public interface PatientService {
    PatientResponseDTO createPatient(PatientRequestDTO requestDTO);

    PatientResponseDTO getPatientById(Long id);

    List<PatientResponseDTO> getAllPatients();

    PatientResponseDTO updatePatient(Long id, PatientRequestDTO requestDTO);

    void deletePatient(Long id);
}
