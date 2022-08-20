package com.example.miniprojecthospitalkelompok2.controller;
import com.example.miniprojecthospitalkelompok2.entity.Users;
import com.example.miniprojecthospitalkelompok2.payload.request.LoginRequest;
import com.example.miniprojecthospitalkelompok2.payload.response.CommonResponse;
import com.example.miniprojecthospitalkelompok2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Users user = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
            if (user == null) {
                return CommonResponse.fail("User not found");
            }
            if (!loginRequest.getPassword().equals(user.getPassword())) {
                return CommonResponse.fail("Password do not match");
            }
            return CommonResponse.common("OK", HttpStatus.OK, user);
        } catch (Exception e) {
            return CommonResponse.fail(e.getMessage());
        }
    }
}