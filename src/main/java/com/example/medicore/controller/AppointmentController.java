package com.example.medicore.controller;


import com.example.medicore.entity.Appointment;
import com.example.medicore.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService service;

    @PostMapping
    public Appointment createAppointment(@RequestParam Long patientId,
                                         @RequestParam Long doctorId,
                                         @RequestBody Appointment appointment) {
        return service.createAppointment(patientId, doctorId, appointment);
    }

    @GetMapping
    public List<Appointment> getAll() {
        return service.getAll();
    }





}
