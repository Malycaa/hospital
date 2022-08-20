package com.example.miniprojecthospitalkelompok2.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.miniprojecthospitalkelompok2.entity.Medication;
import com.example.miniprojecthospitalkelompok2.payload.request.IgnoreRequest;
import com.example.miniprojecthospitalkelompok2.payload.request.InquiryName;
import com.example.miniprojecthospitalkelompok2.payload.response.CommonResponse;
import com.example.miniprojecthospitalkelompok2.repository.MedicationRepository;
import com.example.miniprojecthospitalkelompok2.service.MedicationService;
import com.example.miniprojecthospitalkelompok2.utils.Consts;

import java.util.List;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/medication")
public class MedicationController {
    @Autowired
    MedicationRepository medicationRepository;

    @Autowired
    MedicationService medicationService;


    @PostMapping("/addMedication")
    public ResponseEntity<?> addMedication(@Valid @RequestBody IgnoreRequest.AddMedication request) {
        try {
            medicationRepository.save(Consts.toMedication(request));
            return CommonResponse.success("Medication Registered");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PutMapping("/editMedication")
    public ResponseEntity<?> editMedication(@Valid @RequestBody IgnoreRequest.EditMedication request) {
        try {
            medicationRepository.save(Consts.toMedication(request));
            return CommonResponse.success("Medication Updated");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }


    @DeleteMapping("/deleteMedicationById/{medication_id}")
    public ResponseEntity<Object> deleteMedicationById(@PathVariable Long medication_id) {
        try {
            medicationRepository.deleteById(medication_id);
            return CommonResponse.success("Medication Deleted");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }


    @PostMapping("/inquiryMedication")
    public ResponseEntity<?> inquiryMedication(@RequestBody InquiryName request) {
        try {
            List<Medication> lists = medicationService.inquiryMedication(request.getValue());
            return CommonResponse.common("OK", HttpStatus.OK, lists);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }
}
