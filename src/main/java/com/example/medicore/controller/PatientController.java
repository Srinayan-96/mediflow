package com.example.medicore.controller;

import com.example.medicore.api.ApiResponse;
import com.example.medicore.dto.PatientRequestDTO;
import com.example.medicore.dto.PatientResponseDTO;
import com.example.medicore.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService service;

    @PostMapping
    public ApiResponse<PatientResponseDTO> create(
            @Valid @RequestBody PatientRequestDTO request) {

        return new ApiResponse<>(
                true,
                "Patient created successfully",
                service.create(request)
        );
    }

    @GetMapping
    public ApiResponse<Page<PatientResponseDTO>> getAll(Pageable pageable) {

        return new ApiResponse<>(
                true,
                "Patients fetched successfully",
                service.getAll(pageable)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<PatientResponseDTO> getById(@PathVariable Long id) {

        return new ApiResponse<>(
                true,
                "Patient fetched successfully",
                service.getById(id)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {

        service.delete(id);

        return new ApiResponse<>(
                true,
                "Patient deleted successfully",
                null
        );
    }

}
