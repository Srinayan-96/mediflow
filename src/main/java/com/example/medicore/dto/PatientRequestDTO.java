package com.example.medicore.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PatientRequestDTO {
    @NotBlank(message="Name is required")
    private String name;

    @Min(value=0,message="Age must be Positive")
    private Integer age;

    @NotBlank(message="Gender is required")
    private String gender;

    @NotBlank(message="Phone-number is required")
    private String phone;

    private String address;

}
