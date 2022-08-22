package com.example.miniprojecthospitalkelompok2.utils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.example.miniprojecthospitalkelompok2.entity.Medication;
import com.example.miniprojecthospitalkelompok2.entity.Patients;
import com.example.miniprojecthospitalkelompok2.entity.Treatments;
import com.example.miniprojecthospitalkelompok2.entity.Users;
import com.example.miniprojecthospitalkelompok2.payload.request.AdminRequest;
import com.example.miniprojecthospitalkelompok2.payload.request.MedicationRequest;
import com.example.miniprojecthospitalkelompok2.payload.request.PatientRequest;
import com.example.miniprojecthospitalkelompok2.payload.request.TreatmentRequest;

public class Consts {
    public static Users toUser(AdminRequest request) {
        Users model = new Users();
        if (request.getUser_id() != null) {
            model.setUser_id(request.getUser_id());
        }
        if (request.getRole() != null && request.getRole() != "") {
            model.setRole(request.getRole());
        }
        if (request.getPassword() != null && request.getPassword() != "") {
            model.setPassword(request.getPassword());
        }
        model.setFull_name(request.getFull_name());
        model.setUsername(request.getUsername());
        model.setAddress(request.getAddress());
        model.setGender(request.getGender());
        model.setEmail(request.getEmail());
        model.setAge(request.getAge());

        return model;
    }

    public static Patients patientWithoutList(PatientRequest req, Users user) {
        Patients model = new Patients();
        if (req.getPatient_id() != null) {
            model.setPatient_id(req.getPatient_id());
        }

        model.setPatient_name(req.getPatient_name());
        model.setBirth_place(req.getBirth_place());
        model.setBirth_date(req.getBirth_date());
        model.setAddress(req.getAddress());
        model.setGender(req.getGender());
        model.setComplaints(req.getComplaints());
        model.setRegistrationDate(Instant.now());
        model.setUsers(user);
        return model;
    }

    public static Treatments toTreatment(TreatmentRequest req) {
        List<Medication> list = new ArrayList<Medication>();
        Treatments model = new Treatments();
        if (req.getTreatment_id() != null) {
            model.setTreatment_id(req.getTreatment_id());
        }

        req.getMedications().forEach((i) -> {
            Medication newModel = new Medication();
            newModel.setMedication_id(i.getMedication_id());
            list.add(newModel);
        });

        model.setSickness(req.getSickness());
        model.setSickness_desc(req.getSickness_desc());
        model.setSickness_handling(req.getSickness_handling());
        model.setCreateTime(Instant.now());
        model.setMedications(list);
        return model;
    }

    public static Medication toMedication(MedicationRequest req) {
        Medication model = new Medication();
        if (req.getMedication_id() != null) {
            model.setMedication_id(req.getMedication_id());
        }

        model.setMedication_name(req.getMedication_name());
        model.setMedication_dose(req.getMedication_dose());
        return model;
    }
}
