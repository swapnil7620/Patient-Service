package com.swapnil.patient_service.service;



import com.swapnil.patient_service.dto.AllergyRequestDTO;
import com.swapnil.patient_service.dto.AllergyResponseDTO;

import java.util.List;

public interface AllergyService {

    AllergyResponseDTO addAllergy(Long patientId,
                                  AllergyRequestDTO requestDTO);


    List<AllergyResponseDTO> getAllergiesByPatientId(Long patientId);


    AllergyResponseDTO getAllergyById(Long patientId,
                                      Long allergyId);


    AllergyResponseDTO updateAllergy(Long patientId,
                                     Long allergyId,
                                     AllergyRequestDTO requestDTO);


    void deleteAllergy(Long patientId,
                       Long allergyId);
}
