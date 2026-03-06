package com.example.medicore.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AutoAppointmentRequestDTO {

    private Long patientId;
    private String specialization;
    private LocalDate appointmentDate;

}