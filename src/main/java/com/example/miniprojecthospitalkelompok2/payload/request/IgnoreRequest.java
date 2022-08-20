package com.example.miniprojecthospitalkelompok2.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class IgnoreRequest {

    @JsonIgnoreProperties({"user_id", "role"})
    public static class AddAdmin extends AdminRequest {}
    
    @JsonIgnoreProperties("role")
    public static class EditAdmin extends AdminRequest {}

    @JsonIgnoreProperties("patient_id")
    public static class AddPatient extends PatientRequest {}

    public static class EditPatient extends PatientRequest {}
}
