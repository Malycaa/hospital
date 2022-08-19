package com.example.miniprojecthospitalkelompok2.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_table")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String username;

    private String gender;

    private String email;

    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role;

    private String password;

    public Users(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
