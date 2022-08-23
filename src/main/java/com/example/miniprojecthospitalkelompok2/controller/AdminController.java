package com.example.miniprojecthospitalkelompok2.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.miniprojecthospitalkelompok2.common.USER_ENUM;
import com.example.miniprojecthospitalkelompok2.entity.Users;
import com.example.miniprojecthospitalkelompok2.payload.request.IgnoreRequest;
import com.example.miniprojecthospitalkelompok2.payload.request.InquiryName;
import com.example.miniprojecthospitalkelompok2.payload.response.CommonResponse;
import com.example.miniprojecthospitalkelompok2.repository.AdminRepository;
import com.example.miniprojecthospitalkelompok2.repository.UserRepository;
import com.example.miniprojecthospitalkelompok2.service.AdminService;
import com.example.miniprojecthospitalkelompok2.utils.AdminConvert;
import com.example.miniprojecthospitalkelompok2.utils.Consts;

@CrossOrigin(origins = "https://hospitalcenter-id.herokuapp.com")
@RestController
@RequestMapping("/api/account")
public class AdminController {
    @Autowired
    AdminRepository repository;

    @Autowired
    AdminService service;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/addAdmin")
    public ResponseEntity<?> addAdmin(@Valid @RequestBody IgnoreRequest.AddAdmin request) {
        try {
            if (userRepository.existsByUsername(request.getUsername())) {
                return CommonResponse.fail("Username not available");
            }
            if (userRepository.existsByEmail(request.getEmail())) {
                return CommonResponse.fail("Email not available");
            }

            request.setRole(USER_ENUM.USER_TYPE_ADMIN.name());
            userRepository.save(Consts.toUser(request));
            return CommonResponse.success("Admin Registered");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<?> addDoctor(@Valid @RequestBody IgnoreRequest.AddAdmin request) {
        try {
            if (userRepository.existsByUsername(request.getUsername())) {
                return CommonResponse.fail("Username not available");
            }
            if (userRepository.existsByEmail(request.getEmail())) {
                return CommonResponse.fail("Email not available");
            }

            request.setRole(USER_ENUM.USER_TYPE_DOCTOR.name());
            userRepository.save(Consts.toUser(request));
            return CommonResponse.success("Doctor Registered");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/inquiryDoctor")
    public ResponseEntity<?> inquiryDoctor(@RequestBody InquiryName request) {
        try {
            AdminConvert item = new AdminConvert(request.getValue(), USER_ENUM.USER_TYPE_DOCTOR.name());
            List<Users> lists = service.inquiryUsers(item);
            return CommonResponse.common("OK", HttpStatus.OK, lists);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PostMapping("/inquiryAdmin")
    public ResponseEntity<?> inquiryAdmin(@RequestBody InquiryName request) {
        try {
            AdminConvert item = new AdminConvert(request.getValue(), USER_ENUM.USER_TYPE_ADMIN.name());
            List<Users> lists = service.inquiryUsers(item);
            return CommonResponse.common("OK", HttpStatus.OK, lists);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @PutMapping("/updateAdmin")
    public ResponseEntity<Object> updateAdmin(@RequestBody IgnoreRequest.EditAdmin req) {
        try {
            if (userRepository.existsByUsername(req.getUsername())) {
                return CommonResponse.fail("Username not available");
            }
            if (userRepository.existsByEmail(req.getEmail())) {
                return CommonResponse.fail("Email not available");
            }
            req.setRole(USER_ENUM.USER_TYPE_ADMIN.name());
            userRepository.save(Consts.toUser(req));
            Users user = userRepository.findById(req.getUser_id()).orElse(null);
            return CommonResponse.common("OK", HttpStatus.OK, user);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }


    @PutMapping("/updateDoctor")
    public ResponseEntity<Object> updateDoctor(@RequestBody IgnoreRequest.EditAdmin req) {
        try {
            if (userRepository.existsByUsername(req.getUsername())) {
                return CommonResponse.fail("Username not available");
            }
            if (userRepository.existsByEmail(req.getEmail())) {
                return CommonResponse.fail("Email not available");
            }
            req.setRole(USER_ENUM.USER_TYPE_DOCTOR.name());
            userRepository.save(Consts.toUser(req));
            Users user = userRepository.findById(req.getUser_id()).orElse(null);
            return CommonResponse.common("OK", HttpStatus.OK, user);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @DeleteMapping("/deleteUserById/{user_id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long user_id) {
        try {
            userRepository.deleteById(user_id);
            return CommonResponse.success("User Deleted");
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }

    @GetMapping("/getUserById/{user_id}")
    public ResponseEntity<?> getUserById(@PathVariable Long user_id) {
        try {
            Users user = userRepository.findById(user_id).orElse(null);
            return CommonResponse.common("OK", HttpStatus.OK, user);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }
}
