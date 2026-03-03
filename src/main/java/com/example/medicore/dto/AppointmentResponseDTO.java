package com.example.medicore.dto;


import com.example.medicore.entity.Doctor;
import com.example.medicore.entity.Patient;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AppointmentResponseDTO {
    private Long id;
    private LocalDate appointmentDate;
    private String status;

    private Long PatientId;
    private String PatientName;

    private Long doctorId;
    private String DoctorName;
}
