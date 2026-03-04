package com.example.medicore.service;

import com.example.medicore.dto.PatientRequestDTO;
import com.example.medicore.dto.PatientResponseDTO;
import com.example.medicore.entity.Patient;
import com.example.medicore.exception.ResourceNotFoundException;
import com.example.medicore.repository.PatientRepository;


import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class PatientService {

    public final PatientRepository repository;

    // CREATE
    public PatientResponseDTO create(PatientRequestDTO request) {

        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setAge(request.getAge());
        patient.setGender(request.getGender());
        patient.setPhone(request.getPhone());
        patient.setAddress(request.getAddress());

        Patient saved = repository.save(patient);

        return mapToResponse(saved);
    }

    // GET ALL
    public Page<PatientResponseDTO> getAll(Pageable pageable) {

        Page<Patient> patients = repository.findAll(pageable);

        return patients.map(this::mapToResponse);
    }

    // GET BY ID
    public PatientResponseDTO getById(Long id) {
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not Found!"));

        return mapToResponse(patient);
    }

    // DELETE
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not Found!");
        }
        repository.deleteById(id);
    }

    // 🔥 ENTITY → RESPONSE DTO
    private PatientResponseDTO mapToResponse(Patient patient) {

        PatientResponseDTO response = new PatientResponseDTO();

        response.setId(patient.getId());
        response.setName(patient.getName());
        response.setAge(patient.getAge());
        response.setGender(patient.getGender());
        response.setPhone(patient.getPhone());
        response.setAddress(patient.getAddress());

        return response;
    }

}
