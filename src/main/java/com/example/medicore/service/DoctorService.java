package com.example.medicore.service;


import com.example.medicore.dto.DoctorRequestDTO;
import com.example.medicore.dto.DoctorResponseDTO;
import com.example.medicore.entity.Doctor;
import com.example.medicore.exception.ResourceNotFoundException;
import com.example.medicore.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository repository;


    // ✅ CREATE
    public DoctorResponseDTO create(DoctorRequestDTO request) {

        Doctor doctor = new Doctor();
        doctor.setName(request.getName());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setDepartment(request.getDepartment());
        doctor.setActive(request.isActive());

        Doctor saved = repository.save(doctor);

        return mapToResponse(saved);
    }

    // ✅ GET ALL
    public List<DoctorResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ✅ GET BY ID
    public DoctorResponseDTO getById(Long id) {
        Doctor doctor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        return mapToResponse(doctor);
    }

    // ✅ UPDATE
    public DoctorResponseDTO update(Long id, DoctorRequestDTO request) {

        Doctor doctor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        doctor.setName(request.getName());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setDepartment(request.getDepartment());
        doctor.setActive(request.isActive());

        Doctor updated = repository.save(doctor);

        return mapToResponse(updated);
    }

    // ✅ SOFT DELETE (Deactivate)
    public void delete(Long id) {

        Doctor doctor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        doctor.setActive(false);   // 🔥 soft delete
        repository.save(doctor);
    }

    // 🔁 ENTITY → RESPONSE DTO
    private DoctorResponseDTO mapToResponse(Doctor doctor) {

        DoctorResponseDTO response = new DoctorResponseDTO();

        response.setId(doctor.getId());
        response.setName(doctor.getName());
        response.setSpecialization(doctor.getSpecialization());
        response.setDepartment(doctor.getDepartment());
        response.setActive(doctor.isActive());

        return response;
    }
}
