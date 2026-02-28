package com.example.medicore.service;


import com.example.medicore.entity.Doctor;
import com.example.medicore.exception.ResourceNotFoundException;
import com.example.medicore.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository repository;

    public Doctor create(Doctor doctor){
        return repository.save(doctor);
    }

    public List<Doctor> getAll(){
        return repository.findAll();
    }

    public Doctor getById(Long id){
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Docter not found!!"));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
