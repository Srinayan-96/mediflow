package com.example.medicore.controller;


import com.example.medicore.api.ApiResponse;
import com.example.medicore.dto.DoctorRequestDTO;
import com.example.medicore.dto.DoctorResponseDTO;
import com.example.medicore.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<DoctorResponseDTO> create(
            @Valid @RequestBody DoctorRequestDTO request) {

        return new ApiResponse<>(
                true,
                "Doctor created successfully",
                doctorService.create(request)
        );
    }
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping
    public ApiResponse<Page<DoctorResponseDTO>> getAllDoctors(Pageable pageable){

        return new ApiResponse<>(
                true,
                "Doctors fetched successfully",
                doctorService.getAll(pageable)
        );
    }
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping("/{id}")
    public ApiResponse<DoctorResponseDTO> getById(@PathVariable Long id) {

        return new ApiResponse<>(
                true,
                "Doctor fetched successfully",
                doctorService.getById(id)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<DoctorResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequestDTO request) {

        return new ApiResponse<>(
                true,
                "Doctor updated successfully",
                doctorService.update(id, request)
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {

        doctorService.delete(id);

        return new ApiResponse<>(
                true,
                "Doctor deactivated successfully",
                null
        );
    }
}
