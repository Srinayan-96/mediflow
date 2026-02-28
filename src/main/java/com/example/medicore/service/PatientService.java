package com.example.medicore.service;

import com.example.medicore.entity.Patient;
import com.example.medicore.exception.ResourceNotFoundException;
import com.example.medicore.repository.PatientRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PatientService {

    public final PatientRepository repository;

    public Patient create(Patient patient){
        return repository.save(patient);
    }

    public List<Patient> getAll(){
        return repository.findAll();
    }

    public Patient getById(Long id){
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Patient not Found!"));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
