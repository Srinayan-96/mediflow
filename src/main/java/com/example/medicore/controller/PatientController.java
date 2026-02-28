package com.example.medicore.controller;

import com.example.medicore.entity.Patient;
import com.example.medicore.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService service;

    @PostMapping
    public Patient create(@RequestBody Patient patient){
        return service.create(patient);
    }

    @GetMapping
    public List<Patient> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }



}
