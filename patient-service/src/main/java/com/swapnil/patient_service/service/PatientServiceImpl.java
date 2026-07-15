package com.swapnil.patient_service.service;



import com.swapnil.patient_service.dto.PatientRequestDTO;
import com.swapnil.patient_service.dto.PatientResponseDTO;
import com.swapnil.patient_service.entity.Patient;
import com.swapnil.patient_service.exception.DuplicateEmailException;
import com.swapnil.patient_service.exception.DuplicatePhoneNumberException;
import com.swapnil.patient_service.exception.PatientNotFoundException;
import com.swapnil.patient_service.mapper.PatientMapper;
import com.swapnil.patient_service.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO requestDTO) {

        if (patientRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateEmailException("Email already exists.");
        }

        if (patientRepository.existsByPhoneNumber(requestDTO.getPhoneNumber())) {
             throw new DuplicatePhoneNumberException("PhoneNumber already exists.");
        }

        Patient patient = patientMapper.toEntity(requestDTO);

        Patient savedPatient = patientRepository.save(patient);

        return patientMapper.toResponseDTO(savedPatient);
    }

    @Override
    public PatientResponseDTO getPatientById(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new PatientNotFoundException("Patient not found with ID: " + id));

        return patientMapper.toResponseDTO(patient);
    }

    @Override
    public List<PatientResponseDTO> getAllPatients() {

        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toResponseDTO)
                .toList();
    }

    @Override
    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO requestDTO) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new PatientNotFoundException("Patient not found with ID: " + id));

        patientMapper.updateEntity(patient, requestDTO);

        Patient updatedPatient = patientRepository.save(patient);

        return patientMapper.toResponseDTO(updatedPatient);
    }

    @Override
    public void deletePatient(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new PatientNotFoundException("Patient not found with ID: " + id));

        patientRepository.delete(patient);
    }
}