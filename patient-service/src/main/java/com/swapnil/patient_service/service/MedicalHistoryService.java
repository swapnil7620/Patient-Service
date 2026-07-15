package com.swapnil.patient_service.service;



import com.swapnil.patient_service.dto.MedicalHistoryRequestDTO;
import com.swapnil.patient_service.dto.MedicalHistoryResponseDTO;

public interface MedicalHistoryService {

    MedicalHistoryResponseDTO addMedicalHistory(Long patientId,
                                                MedicalHistoryRequestDTO requestDTO);

    MedicalHistoryResponseDTO getMedicalHistoryByPatientId(Long patientId);

    MedicalHistoryResponseDTO updateMedicalHistory(Long patientId,
                                                   MedicalHistoryRequestDTO requestDTO);

    void deleteMedicalHistory(Long patientId);
}
