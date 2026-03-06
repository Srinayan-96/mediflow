package com.example.medicore.repository;

import com.example.medicore.entity.Appointment;
import com.example.medicore.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    long countByDoctorAndAppointmentDate(Doctor doctor, LocalDate appointmentDate);
}
