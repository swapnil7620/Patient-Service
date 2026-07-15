package com.swapnil.patient_service.service;



import com.swapnil.patient_service.dto.MedicalHistoryRequestDTO;
import com.swapnil.patient_service.dto.MedicalHistoryResponseDTO;
import com.swapnil.patient_service.entity.MedicalHistory;
import com.swapnil.patient_service.entity.Patient;
import com.swapnil.patient_service.exception.MedicalHistoryAlreadyExistsException;
import com.swapnil.patient_service.exception.MedicalHistoryNotFoundException;
import com.swapnil.patient_service.exception.PatientNotFoundException;
import com.swapnil.patient_service.mapper.MedicalHistoryMapper;
import com.swapnil.patient_service.repository.MedicalHistoryRepository;
import com.swapnil.patient_service.repository.PatientRepository;
import com.swapnil.patient_service.service.MedicalHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private final PatientRepository patientRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final MedicalHistoryMapper medicalHistoryMapper;

    @Override
    public MedicalHistoryResponseDTO addMedicalHistory(Long patientId,
                                                       MedicalHistoryRequestDTO requestDTO) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new PatientNotFoundException("Patient not found with ID : " + patientId));

        medicalHistoryRepository.findByPatientId(patientId)
                .ifPresent(history -> {
                    throw new MedicalHistoryAlreadyExistsException("Medical history already exists for this patient.");
                });

        MedicalHistory medicalHistory = medicalHistoryMapper.toEntity(requestDTO);

        medicalHistory.setPatient(patient);

        MedicalHistory savedHistory = medicalHistoryRepository.save(medicalHistory);

        return medicalHistoryMapper.toResponseDTO(savedHistory);
    }

    @Override
    public MedicalHistoryResponseDTO getMedicalHistoryByPatientId(Long patientId) {

        MedicalHistory medicalHistory = medicalHistoryRepository.findByPatientId(patientId)
                .orElseThrow(() ->
                        new MedicalHistoryNotFoundException("Medical history not found."));

        return medicalHistoryMapper.toResponseDTO(medicalHistory);
    }

    @Override
    public MedicalHistoryResponseDTO updateMedicalHistory(Long patientId,
                                                          MedicalHistoryRequestDTO requestDTO) {

        MedicalHistory medicalHistory = medicalHistoryRepository.findByPatientId(patientId)
                .orElseThrow(() ->
                        new MedicalHistoryNotFoundException("Medical history not found."));

        medicalHistoryMapper.updateEntity(medicalHistory, requestDTO);

        MedicalHistory updatedHistory = medicalHistoryRepository.save(medicalHistory);

        return medicalHistoryMapper.toResponseDTO(updatedHistory);
    }

    @Override
    public void deleteMedicalHistory(Long patientId) {

        MedicalHistory medicalHistory = medicalHistoryRepository.findByPatientId(patientId)
                .orElseThrow(() ->
                        new MedicalHistoryNotFoundException("Medical history not found."));

        medicalHistoryRepository.delete(medicalHistory);
    }
}