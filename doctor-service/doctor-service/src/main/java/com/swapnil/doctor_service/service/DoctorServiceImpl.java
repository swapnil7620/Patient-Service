package com.swapnil.doctor_service.service;



import com.swapnil.doctor_service.dto.DoctorRequestDTO;
import com.swapnil.doctor_service.dto.DoctorResponseDTO;
import com.swapnil.doctor_service.entity.Doctor;
import com.swapnil.doctor_service.exception.DoctorNotFoundException;
import com.swapnil.doctor_service.exception.DuplicateEmailException;
import com.swapnil.doctor_service.exception.DuplicatePhoneNumberException;
import com.swapnil.doctor_service.mapper.DoctorMapper;

import com.swapnil.doctor_service.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public DoctorResponseDTO createDoctor(DoctorRequestDTO requestDTO) {

        if (doctorRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateEmailException("Email already exists.");
        }

        if (doctorRepository.existsByPhoneNumber(requestDTO.getPhoneNumber())) {
            throw new DuplicatePhoneNumberException("Phone number already exists.");
        }

        Doctor doctor = DoctorMapper.toEntity(requestDTO);

        Doctor savedDoctor = doctorRepository.save(doctor);

        return DoctorMapper.toResponseDTO(savedDoctor);
    }

    @Override
    public DoctorResponseDTO getDoctorById(Long doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor not found with ID : " + doctorId));

        return DoctorMapper.toResponseDTO(doctor);
    }

    @Override
    public List<DoctorResponseDTO> getAllDoctors() {

        return doctorRepository.findAll()
                .stream()
                .map(DoctorMapper::toResponseDTO)
                .toList();
    }

    @Override
    public DoctorResponseDTO updateDoctor(Long doctorId,
                                          DoctorRequestDTO requestDTO) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor not found with ID : " + doctorId));

        doctorRepository.findByEmail(requestDTO.getEmail())
                .ifPresent(existingDoctor -> {
                    if (!existingDoctor.getId().equals(doctorId)) {
                        throw new DuplicateEmailException("Email already exists.");
                    }
                });

        doctorRepository.findByPhoneNumber(requestDTO.getPhoneNumber())
                .ifPresent(existingDoctor -> {
                    if (!existingDoctor.getId().equals(doctorId)) {
                        throw new DuplicatePhoneNumberException("Phone number already exists.");
                    }
                });

        doctor.setFirstName(requestDTO.getFirstName());
        doctor.setLastName(requestDTO.getLastName());
        doctor.setGender(requestDTO.getGender());
        doctor.setSpecialization(requestDTO.getSpecialization());
        doctor.setQualification(requestDTO.getQualification());
        doctor.setExperienceYears(requestDTO.getExperienceYears());
        doctor.setConsultationFee(requestDTO.getConsultationFee());
        doctor.setEmail(requestDTO.getEmail());
        doctor.setPhoneNumber(requestDTO.getPhoneNumber());

        Doctor updatedDoctor = doctorRepository.save(doctor);

        return DoctorMapper.toResponseDTO(updatedDoctor);
    }

    @Override
    public void deleteDoctor(Long doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor not found with ID : " + doctorId));

        doctorRepository.delete(doctor);
    }
}
