package com.example.miniprojecthospitalkelompok2.repository;

import com.example.miniprojecthospitalkelompok2.common.USER_ENUM;
import com.example.miniprojecthospitalkelompok2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(USER_ENUM name);
}