package com.example.miniprojecthospitalkelompok2.payload.request;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientRequest {
    private Long patient_id;

    private Long user_id;

    private String patient_name;

    private String birth_place;

    private String birth_date;

    private String address;

    private String gender;

    private String complaints;

    private Instant registrationDate;
}
