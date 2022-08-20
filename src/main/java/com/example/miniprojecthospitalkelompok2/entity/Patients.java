package com.example.miniprojecthospitalkelompok2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients_table")
public class Patients implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patient_id;

    private String patient_name;

    private String birth_place;

    private String birth_date;

    private String address;

    private String gender;

    private String complaints;

    private Instant registrationDate;

    @OneToMany(targetEntity = Treatments.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "patients_id", referencedColumnName = "patient_id")
    private List<Treatments> treatments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}
