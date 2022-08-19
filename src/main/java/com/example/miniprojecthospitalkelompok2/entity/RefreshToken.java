package com.example.miniprojecthospitalkelompok2.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "refreshtoken")
@Data
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "users_id", referencedColumnName = "user_id")
    private Users users;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    public RefreshToken() {
    }



}