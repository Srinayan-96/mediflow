package com.example.medicore.controller;


import com.example.medicore.dto.AppointmentRequestDTO;
import com.example.medicore.dto.AppointmentResponseDTO;
import com.example.medicore.entity.Appointment;
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
    public AppointmentResponseDTO create(
            @   Valid @RequestBody AppointmentRequestDTO request) {
        return service.create(request);
    }

    @GetMapping
    public List<AppointmentResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AppointmentResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public AppointmentResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody AppointmentRequestDTO request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


}
