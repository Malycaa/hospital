package com.example.miniprojecthospitalkelompok2.controller;
import org.springframework.http.HttpStatus;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.miniprojecthospitalkelompok2.entity.Patients;
import com.example.miniprojecthospitalkelompok2.entity.Users;
import com.example.miniprojecthospitalkelompok2.payload.request.IgnoreRequest;
import com.example.miniprojecthospitalkelompok2.payload.request.InquiryName;
import com.example.miniprojecthospitalkelompok2.payload.request.PatientInquiry;
import com.example.miniprojecthospitalkelompok2.payload.response.CommonResponse;
import com.example.miniprojecthospitalkelompok2.repository.PatientRepository;
import com.example.miniprojecthospitalkelompok2.repository.UserRepository;
import com.example.miniprojecthospitalkelompok2.service.PatientService;
import com.example.miniprojecthospitalkelompok2.utils.Consts;

@CrossOrigin(origins = "https://hospitalcenter-id.herokuapp.com")
@RestController
@RequestMapping("/api/patient")
public class PatientController {
    
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientService patientService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/addPatient")
    public ResponseEntity<?> addPatient(@Valid @RequestBody IgnoreRequest.AddPatient request) {
        try {
            Users user = userRepository.findById(request.getUser_id()).orElse(null);
            patientRepository.save(Consts.patientWithoutList(request, user));
            return CommonResponse.success("Admin Registered");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PutMapping("/editPatient")
    public ResponseEntity<?> editPatient(@Valid @RequestBody IgnoreRequest.EditPatient request) {
        try {
            Users user = userRepository.findById(request.getUser_id()).orElse(null);
            patientRepository.save(Consts.patientWithoutList(request, user));
            return CommonResponse.success("Admin Registered");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }


    @PostMapping("/inquiryPatient")
    public ResponseEntity<?> inquiryPatient(@RequestBody PatientInquiry request) {
        try {
            List<Patients> lists = patientService.inquiryPatient(request);
            return CommonResponse.common("OK", HttpStatus.OK, lists);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }


    @PostMapping("/inquiryPatientByAdmin")
    public ResponseEntity<?> inquiryPatientByAdmin(@RequestBody InquiryName request) {
        try {
            List<Patients> lists = patientService.inquiryPatientByAdmin(request.getValue());
            return CommonResponse.common("OK", HttpStatus.OK, lists);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }


    @DeleteMapping("/deletePatientById/{patient_id}")
    public ResponseEntity<Object> deletePatientById(@PathVariable Long patient_id) {
        try {
            patientRepository.deleteById(patient_id);
            return CommonResponse.success("Patient Deleted");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }
    @GetMapping("/getPatientById/{patient_id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long patient_id) {
        try {
            Patients patients = patientRepository.findById(patient_id).orElse(null);
            return CommonResponse.common("OK", HttpStatus.OK, patients);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }
}
