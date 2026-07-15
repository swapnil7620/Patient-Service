package com.swapnil.patient_service.mapper;

import org.springframework.stereotype.Component;



import com.swapnil.patient_service.dto.PatientRequestDTO;
import com.swapnil.patient_service.dto.PatientResponseDTO;
import com.swapnil.patient_service.entity.Patient;


@Component
public class PatientMapper {

    /**
     * Convert Request DTO -> Entity
     */
    public Patient toEntity(PatientRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        return Patient.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .gender(dto.getGender())
                .dateOfBirth(dto.getDateOfBirth())
                .bloodGroup(dto.getBloodGroup())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .build();
    }

    /**
     * Convert Entity -> Response DTO
     */
    public PatientResponseDTO toResponseDTO(Patient patient) {

        if (patient == null) {
            return null;
        }

        return PatientResponseDTO.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .gender(patient.getGender())
                .dateOfBirth(patient.getDateOfBirth())
                .bloodGroup(patient.getBloodGroup())
                .email(patient.getEmail())
                .phoneNumber(patient.getPhoneNumber())
                .address(patient.getAddress())
                .createdAt(patient.getCreatedAt())
                .updatedAt(patient.getUpdatedAt())
                .build();
    }

    /**
     * Update Existing Entity
     */
    public void updateEntity(Patient patient, PatientRequestDTO dto) {

        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setGender(dto.getGender());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setBloodGroup(dto.getBloodGroup());
        patient.setEmail(dto.getEmail());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setAddress(dto.getAddress());
    }
}
