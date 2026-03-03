package com.example.medicore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorRequestDTO {

    @NotBlank(message="Name is required")
    private String name;
    @NotBlank(message="Specialization is Required")
    private String specialization;
    @NotBlank(message="Department is required")
    private String department;

    private boolean active=true;

}
