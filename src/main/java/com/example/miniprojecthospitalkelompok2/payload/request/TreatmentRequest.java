package com.example.miniprojecthospitalkelompok2.payload.request;

import java.time.Instant;
import java.util.List;


import lombok.Data;

@Data
public class TreatmentRequest {
    private Long treatment_id;

    private Long patient_id;

    private String sickness;

    private String sickness_desc;

    private String sickness_handling;

    private Instant createTime;

    private List<MedicationTreatmentReq> medications;
}
