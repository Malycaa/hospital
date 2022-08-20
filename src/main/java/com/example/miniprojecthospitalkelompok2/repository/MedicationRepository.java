package com.example.miniprojecthospitalkelompok2.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.miniprojecthospitalkelompok2.entity.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long>{
    @Query(value = "SELECT * FROM medication_table m WHERE m.medication_name LIKE :param%", nativeQuery = true)
    List<Medication> findMedicationByName(String param);
}
