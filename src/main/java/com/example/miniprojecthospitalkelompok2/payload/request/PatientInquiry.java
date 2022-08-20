package com.example.miniprojecthospitalkelompok2.payload.request;

import lombok.Data;

@Data
public class PatientInquiry {
    private String patient_name;
    private Long user_id;
}
