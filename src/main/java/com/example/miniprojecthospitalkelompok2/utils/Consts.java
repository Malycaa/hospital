package com.example.miniprojecthospitalkelompok2.utils;
import com.example.miniprojecthospitalkelompok2.entity.Patients;
import com.example.miniprojecthospitalkelompok2.entity.Users;
import com.example.miniprojecthospitalkelompok2.payload.request.AdminRequest;
import com.example.miniprojecthospitalkelompok2.payload.request.PatientRequest;

public class Consts {
    public static Users toUser(AdminRequest request) {
        Users model = new Users();
        if (request.getRole() != null) {
            model.setRole(request.getRole());
        }
        if (request.getPassword() != null) {
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
        model.setRegistrationDate(req.getRegistrationDate());
        model.setUsers(user);
        return model;
    }
}
