package com.example.miniprojecthospitalkelompok2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
    private String name;
    private String tempatLahir;
    private String tanggalLahir;
    private String address;
    private String jenisKelamin;
    private Date tanggalRegistrasi;
    private String keluhan;

    @OneToMany(targetEntity =Treatments.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "patients_id", referencedColumnName = "patient_id")
    private List<Treatments> treatments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

}
