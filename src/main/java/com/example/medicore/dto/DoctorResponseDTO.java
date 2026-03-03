package com.example.medicore.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorResponseDTO {

    private Long id;
    private String name;
    private String specialization;
    private String department;
    private boolean Active;

}
