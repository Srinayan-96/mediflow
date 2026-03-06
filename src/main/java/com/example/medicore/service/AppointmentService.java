package com.example.medicore.service;


import com.example.medicore.dto.AppointmentRequestDTO;
import com.example.medicore.dto.AppointmentResponseDTO;
import com.example.medicore.entity.Appointment;
import com.example.medicore.entity.Doctor;
import com.example.medicore.entity.Patient;
import com.example.medicore.exception.ResourceNotFoundException;
import com.example.medicore.repository.AppointmentRepository;
import com.example.medicore.repository.DoctorRepository;
import com.example.medicore.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    // CREATE
    public AppointmentResponseDTO create(AppointmentRequestDTO request) {

        Patient patient = patientRepo.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        Doctor doctor = doctorRepo.findById(request.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStatus(request.getStatus());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        Appointment saved = appointmentRepo.save(appointment);

        return mapToResponse(saved);
    }

    // GET ALL
    public Page<AppointmentResponseDTO> getAll(Pageable pageable){

        Page<Appointment> appointments = appointmentRepo.findAll(pageable);

        return appointments.map(this::mapToResponse);
    }

    // GET BY ID
    public AppointmentResponseDTO getById(Long id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        return mapToResponse(appointment);
    }

    // UPDATE
    public AppointmentResponseDTO update(Long id, AppointmentRequestDTO request) {

        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        Patient patient = patientRepo.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        Doctor doctor = doctorRepo.findById(request.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStatus(request.getStatus());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        Appointment updated = appointmentRepo.save(appointment);

        return mapToResponse(updated);
    }

    // DELETE
    public void delete(Long id) {
        if (!appointmentRepo.existsById(id)) {
            throw new ResourceNotFoundException("Appointment not found");
        }
        appointmentRepo.deleteById(id);
    }

    // Auto-Scheduler Method
    public Appointment autoSchedule(Long patientId, String specialization, LocalDate date) {

        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        List<Doctor> doctors = doctorRepo.findBySpecialization(specialization);

        if (doctors.isEmpty()) {
            throw new RuntimeException("No doctors available for this specialization");
        }

        Doctor bestDoctor = doctors.stream()
                .min(Comparator.comparingLong(doc ->
                        appointmentRepo.countByDoctorAndAppointmentDate(doc, date)))
                .orElseThrow();

        Appointment appointment = new Appointment();

        appointment.setPatient(patient);
        appointment.setDoctor(bestDoctor);
        appointment.setAppointmentDate(date);

        return appointmentRepo.save(appointment);
    }

    //ENTITY → RESPONSE DTO
    private AppointmentResponseDTO mapToResponse(Appointment appointment) {

        AppointmentResponseDTO response = new AppointmentResponseDTO();

        response.setId(appointment.getId());
        response.setAppointmentDate(appointment.getAppointmentDate());
        response.setStatus(appointment.getStatus());

        response.setPatientId(appointment.getPatient().getId());
        response.setPatientName(appointment.getPatient().getName());

        response.setDoctorId(appointment.getDoctor().getId());
        response.setDoctorName(appointment.getDoctor().getName());

        return response;
    }


}