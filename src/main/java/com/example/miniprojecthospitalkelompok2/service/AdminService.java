package com.example.miniprojecthospitalkelompok2.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.miniprojecthospitalkelompok2.entity.Users;
import com.example.miniprojecthospitalkelompok2.repository.AdminRepository;
import com.example.miniprojecthospitalkelompok2.utils.AdminConvert;

@Service
public class AdminService {
    @Autowired
    AdminRepository repository;

    public List<Users> inquiryUsers(AdminConvert param) {
        return repository.findAdminByUsername(param);
    }
}
