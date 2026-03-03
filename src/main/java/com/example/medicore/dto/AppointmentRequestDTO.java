package com.example.medicore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class AppointmentRequestDTO  {

    @NotBlank(message="Appointment date is required")
    private LocalDate appointmentDate;
    @NotBlank(message="Status is required")
    private String status;
    @NotBlank(message="Patient Id is required")
    private long patientId;
    @NotBlank(message="doctor Id is required")
    private long doctorId;
}
