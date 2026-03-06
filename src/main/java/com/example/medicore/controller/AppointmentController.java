package com.example.medicore.controller;


import com.example.medicore.api.ApiResponse;
import com.example.medicore.dto.AppointmentRequestDTO;
import com.example.medicore.dto.AppointmentResponseDTO;
import com.example.medicore.dto.AutoAppointmentRequestDTO;
import com.example.medicore.entity.Appointment;
import com.example.medicore.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


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
    @PostMapping("/auto")
    public ApiResponse<Appointment> autoSchedule(
            @RequestBody AutoAppointmentRequestDTO request) {

        Appointment appointment =   service.autoSchedule(
                request.getPatientId(),
                request.getSpecialization(),
                request.getAppointmentDate()
        );

        return new ApiResponse<>(
                true,
                "Appointment auto scheduled successfully",
                appointment
        );
    }
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @GetMapping
    public ApiResponse<Page<AppointmentResponseDTO>> getAll(Pageable pageable){

        return new ApiResponse<>(
                true,
                "Appointments fetched successfully",
                service.getAll(pageable)
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
