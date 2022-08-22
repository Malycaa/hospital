package com.example.miniprojecthospitalkelompok2.payload.request;

import com.example.miniprojecthospitalkelompok2.entity.Medication;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class IgnoreRequest {

    @JsonIgnoreProperties({"user_id", "role"})
    public static class AddAdmin extends AdminRequest {}
    
    @JsonIgnoreProperties("role")
    public static class EditAdmin extends AdminRequest {}

    @JsonIgnoreProperties({"patient_id", "registrationDate"})
    public static class AddPatient extends PatientRequest {}

    @JsonIgnoreProperties({"registrationDate"})
    public static class EditPatient extends PatientRequest {}

    @JsonIgnoreProperties({"medication_id"})
    public static class AddMedication extends MedicationRequest {}

    public static class EditMedication extends MedicationRequest {}

    @JsonIgnoreProperties({"medication_name", "medication_dose"})
    public static class MedicationTreatment extends Medication {}


    @JsonIgnoreProperties({"treatment_id"})
    public static class AddTreatment extends TreatmentRequest {}
    
    public static class EditTreatment extends TreatmentRequest {}
}
