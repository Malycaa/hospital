package com.example.miniprojecthospitalkelompok2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.miniprojecthospitalkelompok2.entity.Treatments;

public interface TreatmentRepository extends JpaRepository<Treatments, Long> {
    
}
