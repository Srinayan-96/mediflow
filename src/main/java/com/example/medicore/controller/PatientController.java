package com.example.medicore.controller;

import com.example.medicore.dto.PatientRequestDTO;
import com.example.medicore.dto.PatientResponseDTO;
import com.example.medicore.entity.Patient;
import com.example.medicore.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService service;

    @PostMapping
    public PatientResponseDTO create(
            @Valid @RequestBody PatientRequestDTO request) {
        return service.create(request);
    }

    @GetMapping
    public List<PatientResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PatientResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
