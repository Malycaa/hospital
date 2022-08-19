package com.example.miniprojecthospitalkelompok2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "treatment_table")
public class Treatments{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long treatment_id;
    private String namaPenyakit;
    private String penyakitDesc;
    private String penanganan;
    private String obat;
    private String dosis;
    private Date tanggalPembuatan;


}
