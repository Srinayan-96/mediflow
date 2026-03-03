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

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    // ✅ CREATE
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

    // ✅ GET ALL
    public List<AppointmentResponseDTO> getAll() {
        return appointmentRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ✅ GET BY ID
    public AppointmentResponseDTO getById(Long id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        return mapToResponse(appointment);
    }

    // ✅ UPDATE
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

    // ✅ DELETE
    public void delete(Long id) {
        if (!appointmentRepo.existsById(id)) {
            throw new ResourceNotFoundException("Appointment not found");
        }
        appointmentRepo.deleteById(id);
    }

    // 🔁 ENTITY → RESPONSE DTO
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