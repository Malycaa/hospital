package com.example.miniprojecthospitalkelompok2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.miniprojecthospitalkelompok2.repository.PatientRepository;
import com.example.miniprojecthospitalkelompok2.repository.TreatmentRepository;

@Service
public class TreatmentService {
    @Autowired
    TreatmentRepository treatmentRepository;

    @Autowired
    PatientRepository patientRepository;
}
