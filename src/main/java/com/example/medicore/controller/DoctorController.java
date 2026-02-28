package com.example.medicore.controller;


import com.example.medicore.entity.Doctor;
import com.example.medicore.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public Doctor create(@RequestBody Doctor doctor){
        return doctorService.create(doctor);
    }

    @GetMapping
    public List<Doctor> getAllDoctors(){
        return doctorService.getAll();
    }

    @GetMapping("/{id}")
    public Doctor getById(@PathVariable Long id){
        return doctorService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        doctorService.delete(id);
    }

}
