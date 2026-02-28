package com.example.medicore.service;


import com.example.medicore.entity.Appointment;
import com.example.medicore.entity.Doctor;
import com.example.medicore.entity.Patient;
import com.example.medicore.exception.ResourceNotFoundException;
import com.example.medicore.repository.AppointmentRepository;
import com.example.medicore.repository.DoctorRepository;
import com.example.medicore.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    public Appointment createAppointment(Long patientId, Long docterId,Appointment appointment){
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        Doctor doctor = doctorRepo.findById(docterId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        return appointmentRepo.save(appointment);

    }
    public List<Appointment> getAll(){
        return appointmentRepo.findAll();
    }



}
