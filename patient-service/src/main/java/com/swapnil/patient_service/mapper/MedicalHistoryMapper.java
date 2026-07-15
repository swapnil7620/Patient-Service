package com.swapnil.patient_service.mapper;



import com.swapnil.patient_service.dto.MedicalHistoryRequestDTO;
import com.swapnil.patient_service.dto.MedicalHistoryResponseDTO;
import com.swapnil.patient_service.entity.MedicalHistory;
import org.springframework.stereotype.Component;

@Component
public class MedicalHistoryMapper {

    /**
     * Request DTO -> Entity
     */
    public MedicalHistory toEntity(MedicalHistoryRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        return MedicalHistory.builder()
                .medicalCondition(dto.getMedicalCondition())
                .currentMedication(dto.getCurrentMedication())
                .notes(dto.getNotes())
                .build();
    }

    /**
     * Entity -> Response DTO
     */
    public MedicalHistoryResponseDTO toResponseDTO(MedicalHistory medicalHistory) {

        if (medicalHistory == null) {
            return null;
        }

        return MedicalHistoryResponseDTO.builder()
                .id(medicalHistory.getId())
                .medicalCondition(medicalHistory.getMedicalCondition())
                .currentMedication(medicalHistory.getCurrentMedication())
                .notes(medicalHistory.getNotes())
                .updatedAt(medicalHistory.getUpdatedAt())
                .build();
    }

    /**
     * Update Existing Entity
     */
    public void updateEntity(MedicalHistory medicalHistory,
                             MedicalHistoryRequestDTO dto) {

        medicalHistory.setMedicalCondition(dto.getMedicalCondition());
        medicalHistory.setCurrentMedication(dto.getCurrentMedication());
        medicalHistory.setNotes(dto.getNotes());
    }
}