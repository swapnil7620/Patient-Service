package com.swapnil.patient_service.repository;

import com.swapnil.patient_service.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    List<Allergy> findByPatientId(Long patientId);
    Optional<Allergy> findByPatientIdAndAllergyNameIgnoreCase(
            Long patientId,
            String allergyName);

    /*
     * Find a specific allergy belonging to a specific patient
     */
    Optional<Allergy> findByIdAndPatientId(Long allergyId, Long patientId);
}