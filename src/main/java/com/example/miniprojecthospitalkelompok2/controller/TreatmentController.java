package com.example.miniprojecthospitalkelompok2.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.miniprojecthospitalkelompok2.entity.Patients;
import com.example.miniprojecthospitalkelompok2.payload.request.IgnoreRequest;
import com.example.miniprojecthospitalkelompok2.payload.response.CommonResponse;
import com.example.miniprojecthospitalkelompok2.repository.MedicationRepository;
import com.example.miniprojecthospitalkelompok2.repository.PatientRepository;
import com.example.miniprojecthospitalkelompok2.service.MedicationService;
import com.example.miniprojecthospitalkelompok2.utils.Consts;

@CrossOrigin(origins = "https://hospitalcenter-id.herokuapp.com")
@RestController
@RequestMapping("/api/treatment")
public class TreatmentController {
    @Autowired
    MedicationRepository mediRepository;

    @Autowired
    MedicationService mediService;

    @Autowired
    PatientRepository patRepository;

    @PostMapping("/addTreatment")
    public ResponseEntity<?> addTreatment(@Valid @RequestBody IgnoreRequest.AddTreatment request) {
        try {
            Patients pat = patRepository.findById(request.getPatient_id()).orElse(null);
            pat.getTreatments().add(Consts.toTreatment(request));
            patRepository.save(pat);
            return CommonResponse.success("Treatment Registered");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }
}
