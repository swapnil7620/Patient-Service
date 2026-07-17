package com.swapnil.appointment_service.dto;


import com.swapnil.appointment_service.enums.Gender;
import com.swapnil.appointment_service.enums.Specialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponseDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private Gender gender;

    private String qualification;

    private Specialization specialization;

    private Integer experienceYears;

    private BigDecimal consultationFee;

    private String email;

    private String phoneNumber;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
