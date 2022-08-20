package com.example.miniprojecthospitalkelompok2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "treatment_table")
public class Treatments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long treatment_id;

    private String sickness;

    private String sickness_desc;

    private String sickness_handling;

    private Instant createTime;

    // @OneToMany(targetEntity = Medication.class, cascade = CascadeType.ALL)
    // @JoinColumn(name = "treatments_id", referencedColumnName = "treatment_id")
    // private List<Medication> medications;

    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "medication_list", 
    joinColumns = @JoinColumn(name = "treatment_id"), 
    inverseJoinColumns = @JoinColumn(name = "medication_id"))
	private List<Medication> medications;
}
