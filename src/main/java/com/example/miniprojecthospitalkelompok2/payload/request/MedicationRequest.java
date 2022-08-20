package com.example.miniprojecthospitalkelompok2.payload.request;
import lombok.Data;

@Data
public class MedicationRequest {
    private Long medication_id;

    private String medication_name;

    private String medication_dose;
}
