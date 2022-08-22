package com.example.miniprojecthospitalkelompok2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.miniprojecthospitalkelompok2.entity.Patients;
import com.example.miniprojecthospitalkelompok2.payload.request.PatientInquiry;
import com.example.miniprojecthospitalkelompok2.repository.PatientRepository;

@Service
public class PatientService {
    @Autowired
    PatientRepository repository;

    public List<Patients> inquiryPatient(PatientInquiry param){
        return repository.inquiryPatient(param);
    }

    public List<Patients> inquiryPatientByAdmin(String param){
        return repository.inquiryPatientByAdmin(param);
    }
}
