package com.swapnil.doctor_service.dto;



import com.swapnil.doctor_service.enums.Gender;
import com.swapnil.doctor_service.enums.Specialization;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorRequestDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private Gender gender;

    @NotNull
    private Specialization specialization;

    @NotBlank
    private String qualification;

    @NotNull
    @Min(0)
    private Integer experienceYears;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal consultationFee;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "^[6-9]\\d{9}$")
    private String phoneNumber;
}
