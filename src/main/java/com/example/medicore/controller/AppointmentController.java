package com.example.medicore.controller;


import com.example.medicore.api.ApiResponse;
import com.example.medicore.dto.AppointmentRequestDTO;
import com.example.medicore.dto.AppointmentResponseDTO;
import com.example.medicore.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService service;

    @PostMapping
    public ApiResponse<AppointmentResponseDTO> create(
            @Valid @RequestBody AppointmentRequestDTO request) {

        return new ApiResponse<>(
                true,
                "Appointment created successfully",
                service.create(request)
        );
    }

    @GetMapping
    public ApiResponse<List<AppointmentResponseDTO>> getAll() {

        return new ApiResponse<>(
                true,
                "Appointments fetched successfully",
                service.getAll()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<AppointmentResponseDTO> getById(@PathVariable Long id) {

        return new ApiResponse<>(
                true,
                "Appointment fetched successfully",
                service.getById(id)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<AppointmentResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody AppointmentRequestDTO request) {

        return new ApiResponse<>(
                true,
                "Appointment updated successfully",
                service.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {

        service.delete(id);

        return new ApiResponse<>(
                true,
                "Appointment deleted successfully",
                null
        );
    }

}
