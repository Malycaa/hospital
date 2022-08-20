package com.example.miniprojecthospitalkelompok2.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.miniprojecthospitalkelompok2.entity.Medication;
import com.example.miniprojecthospitalkelompok2.repository.MedicationRepository;


@Service
public class MedicationService  {
    @Autowired
    MedicationRepository repository;

    public List<Medication> inquiryMedication(String param){
        return repository.findMedicationByName(param);
    }
}
