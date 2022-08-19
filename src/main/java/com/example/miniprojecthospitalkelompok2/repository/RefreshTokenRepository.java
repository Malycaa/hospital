package com.example.miniprojecthospitalkelompok2.repository;

import com.example.miniprojecthospitalkelompok2.entity.RefreshToken;
import com.example.miniprojecthospitalkelompok2.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUsers(Users users);
}