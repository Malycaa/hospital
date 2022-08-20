package com.example.miniprojecthospitalkelompok2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.miniprojecthospitalkelompok2.entity.Patients;
import com.example.miniprojecthospitalkelompok2.repository.PatientRepository;

@Service
public class PatientService {
    @Autowired
    PatientRepository repository;

    public List<Patients> inquiryPatient(String param){
        return repository.inquiryPatient(param);
    }
}
