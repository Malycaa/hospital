package com.example.miniprojecthospitalkelompok2.entity;

import com.example.miniprojecthospitalkelompok2.common.USER_ENUM;
import lombok.Data;

import javax.persistence.*;

import java.util.List;

@Entity
@Data
@Table(name = "role_table")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private USER_ENUM name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<Users> users;
}
