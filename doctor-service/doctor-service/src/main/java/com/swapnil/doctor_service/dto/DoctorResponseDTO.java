package com.swapnil.doctor_service.dto;


import com.swapnil.doctor_service.enums.Gender;
import com.swapnil.doctor_service.enums.Specialization;
import lombok.*;

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

    private Specialization specialization;

    private String qualification;

    private Integer experienceYears;

    private BigDecimal consultationFee;

    private String email;

    private String phoneNumber;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
