package com.swapnil.doctor_service.mapper;


import com.swapnil.doctor_service.dto.DoctorRequestDTO;
import com.swapnil.doctor_service.dto.DoctorResponseDTO;
import com.swapnil.doctor_service.entity.Doctor;

public class DoctorMapper {

    private DoctorMapper() {
    }

    public static Doctor toEntity(DoctorRequestDTO dto) {

        return Doctor.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .gender(dto.getGender())
                .specialization(dto.getSpecialization())
                .qualification(dto.getQualification())
                .experienceYears(dto.getExperienceYears())
                .consultationFee(dto.getConsultationFee())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public static DoctorResponseDTO toResponseDTO(Doctor doctor) {

        return DoctorResponseDTO.builder()
                .id(doctor.getId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .gender(doctor.getGender())
                .specialization(doctor.getSpecialization())
                .qualification(doctor.getQualification())
                .experienceYears(doctor.getExperienceYears())
                .consultationFee(doctor.getConsultationFee())
                .email(doctor.getEmail())
                .phoneNumber(doctor.getPhoneNumber())
                .createdAt(doctor.getCreatedAt())
                .updatedAt(doctor.getUpdatedAt())
                .build();
    }
}