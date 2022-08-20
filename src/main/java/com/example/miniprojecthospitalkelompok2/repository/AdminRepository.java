package com.example.miniprojecthospitalkelompok2.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.miniprojecthospitalkelompok2.entity.Users;
import com.example.miniprojecthospitalkelompok2.utils.AdminConvert;

@Repository
public interface AdminRepository extends JpaRepository<Users, Long> {
    @Query(value = "SELECT * FROM user_table u WHERE u.username"
            + " LIKE :#{#param.username}% AND u.role = :#{#param.role}", nativeQuery = true)
    List<Users> findAdminByUsername(@Param("param") AdminConvert param);
}
