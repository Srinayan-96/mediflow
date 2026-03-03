package com.example.medicore.controller;


import com.example.medicore.dto.DoctorRequestDTO;
import com.example.medicore.dto.DoctorResponseDTO;
import com.example.medicore.entity.Doctor;
import com.example.medicore.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public DoctorResponseDTO create(
            @Valid @RequestBody DoctorRequestDTO request) {
        return doctorService.create(request);
    }

    @GetMapping
    public List<DoctorResponseDTO> getAllDoctors() {
        return doctorService.getAll();
    }

    @GetMapping("/{id}")
    public DoctorResponseDTO getById(@PathVariable Long id) {
        return doctorService.getById(id);
    }
    @PutMapping("/{id}")
    public DoctorResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequestDTO request) {

        return doctorService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        doctorService.delete(id);
    }
}
