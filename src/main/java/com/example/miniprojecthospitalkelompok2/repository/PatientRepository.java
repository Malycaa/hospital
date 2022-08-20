package com.example.miniprojecthospitalkelompok2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.miniprojecthospitalkelompok2.entity.Patients;

@Repository
public interface PatientRepository extends JpaRepository<Patients, Long>{
    @Query(value = "SELECT * FROM patients_table p WHERE p.patient_name LIKE :param%", nativeQuery = true)
    List<Patients> inquiryPatient(@Param("param") String param);
}
