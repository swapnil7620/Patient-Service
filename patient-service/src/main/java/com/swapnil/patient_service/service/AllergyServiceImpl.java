package com.swapnil.patient_service.service;



import com.swapnil.patient_service.dto.AllergyRequestDTO;
import com.swapnil.patient_service.dto.AllergyResponseDTO;
import com.swapnil.patient_service.entity.Allergy;
import com.swapnil.patient_service.entity.Patient;
import com.swapnil.patient_service.exception.AllergyNotFoundException;
import com.swapnil.patient_service.exception.DuplicateAllergyException;
import com.swapnil.patient_service.exception.PatientNotFoundException;
//import com.swapnil.patient_service.exception.AllergyNotFoundException;
import com.swapnil.patient_service.mapper.AllergyMapper;
import com.swapnil.patient_service.repository.AllergyRepository;
import com.swapnil.patient_service.repository.PatientRepository;
import com.swapnil.patient_service.service.AllergyService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllergyServiceImpl implements AllergyService {


    private final AllergyRepository allergyRepository;

    private final PatientRepository patientRepository;

    private final AllergyMapper allergyMapper;


    @Override
    public AllergyResponseDTO addAllergy(Long patientId,
                                         AllergyRequestDTO requestDTO) {


        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new PatientNotFoundException(
                                "Patient not found with id: " + patientId));


        allergyRepository
                .findByPatientIdAndAllergyNameIgnoreCase(
                        patientId,
                        requestDTO.getAllergyName())
                .ifPresent(a -> {
                    throw new DuplicateAllergyException(
                            "Allergy '" + requestDTO.getAllergyName()
                                    + "' already exists for this patient.");
                });

        Allergy allergy = allergyMapper.toEntity(requestDTO);

        allergy.setPatient(patient);

        Allergy savedAllergy = allergyRepository.save(allergy);


        return allergyMapper.toResponseDTO(savedAllergy);
    }


    @Override
    public List<AllergyResponseDTO> getAllergiesByPatientId(Long patientId) {


        validatePatient(patientId);


        return allergyRepository.findByPatientId(patientId)
                .stream()
                .map(allergyMapper::toResponseDTO)
                .toList();
    }



    @Override
    public AllergyResponseDTO getAllergyById(Long patientId,
                                             Long allergyId) {


        Allergy allergy =
                allergyRepository.findByIdAndPatientId(
                                allergyId,
                                patientId
                        )
                        .orElseThrow(() ->
                                new AllergyNotFoundException(
                                        "Allergy not found with id: " + allergyId));


        return allergyMapper.toResponseDTO(allergy);
    }




    @Override
    public AllergyResponseDTO updateAllergy(Long patientId,
                                            Long allergyId,
                                            AllergyRequestDTO requestDTO) {


        Allergy allergy =
                allergyRepository.findByIdAndPatientId(
                                allergyId,
                                patientId
                        )
                        .orElseThrow(() ->
                                new AllergyNotFoundException(
                                        "Allergy not found with id: " + allergyId));


        allergyMapper.updateEntity(allergy, requestDTO);


        Allergy updatedAllergy =
                allergyRepository.save(allergy);


        return allergyMapper.toResponseDTO(updatedAllergy);
    }



    @Override
    public void deleteAllergy(Long patientId,
                              Long allergyId) {


        Allergy allergy =
                allergyRepository.findByIdAndPatientId(
                                allergyId,
                                patientId
                        )
                        .orElseThrow(() ->
                                new AllergyNotFoundException(
                                        "Allergy not found with id: " + allergyId));


        allergyRepository.delete(allergy);
    }




    private void validatePatient(Long patientId) {

        patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new PatientNotFoundException(
                                "Patient not found with id: " + patientId));
    }

}
