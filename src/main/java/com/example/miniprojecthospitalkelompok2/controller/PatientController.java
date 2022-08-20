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
import com.example.miniprojecthospitalkelompok2.payload.response.CommonResponse;
import com.example.miniprojecthospitalkelompok2.repository.PatientRepository;
import com.example.miniprojecthospitalkelompok2.repository.UserRepository;
import com.example.miniprojecthospitalkelompok2.service.PatientService;
import com.example.miniprojecthospitalkelompok2.utils.Consts;

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
    // @Operation(summary = "addPatient", security = @SecurityRequirement(name = "Authorization"))
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
    // @Operation(summary = "editPatient", security = @SecurityRequirement(name = "Authorization"))
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
    // @Operation(summary = "inquiryPatient", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<?> inquiryPatient(@RequestBody InquiryName request) {
        try {
            List<Patients> lists = patientService.inquiryPatient(request.getValue());
            return CommonResponse.common("OK", HttpStatus.OK, lists);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }


    @DeleteMapping("/deletePatientById/{patient_id}")
    // @Operation(summary = "deletePatientById", security = @SecurityRequirement(name = "Authorization"))
    public ResponseEntity<Object> deletePatientById(@PathVariable Long patient_id) {
        try {
            patientRepository.deleteById(patient_id);
            return CommonResponse.success("Patient Deleted");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }
}