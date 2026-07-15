package com.swapnil.patient_service.mapper;



import com.swapnil.patient_service.dto.AllergyRequestDTO;
import com.swapnil.patient_service.dto.AllergyResponseDTO;
import com.swapnil.patient_service.entity.Allergy;
import com.swapnil.patient_service.enums.Severity;
import org.springframework.stereotype.Component;

@Component
public class AllergyMapper {

    /**
     * Convert Request DTO -> Entity
     */
    public Allergy toEntity(AllergyRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        return Allergy.builder()
                .allergyName(dto.getAllergyName())
                .severity(Severity.valueOf(dto.getSeverity()))
                .identifiedDate(dto.getIdentifiedDate())
                .build();
    }

    /**
     * Convert Entity -> Response DTO
     */
    public AllergyResponseDTO toResponseDTO(Allergy allergy) {

        if (allergy == null) {
            return null;
        }

        return AllergyResponseDTO.builder()
                .id(allergy.getId())
                .allergyName(allergy.getAllergyName())
                .severity(String.valueOf(allergy.getSeverity()))
                .identifiedDate(allergy.getIdentifiedDate())
                .build();
    }

    /**
     * Update Existing Entity
     */
    public void updateEntity(Allergy allergy, AllergyRequestDTO dto) {

        allergy.setAllergyName(dto.getAllergyName());
        allergy.setSeverity(Severity.valueOf(dto.getSeverity()));
        allergy.setIdentifiedDate(dto.getIdentifiedDate());
    }
}