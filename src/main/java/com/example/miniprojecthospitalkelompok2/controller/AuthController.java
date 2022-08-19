package com.example.miniprojecthospitalkelompok2.controller;

import com.example.miniprojecthospitalkelompok2.common.TokenRefreshException;
import com.example.miniprojecthospitalkelompok2.common.USER_ENUM;
import com.example.miniprojecthospitalkelompok2.entity.RefreshToken;
import com.example.miniprojecthospitalkelompok2.entity.Role;
import com.example.miniprojecthospitalkelompok2.entity.Users;
import com.example.miniprojecthospitalkelompok2.payload.request.LogOutRequest;
import com.example.miniprojecthospitalkelompok2.payload.request.LoginRequest;
import com.example.miniprojecthospitalkelompok2.payload.request.SignupRequest;
import com.example.miniprojecthospitalkelompok2.payload.request.TokenRefreshRequest;
import com.example.miniprojecthospitalkelompok2.payload.response.JwtResponse;
import com.example.miniprojecthospitalkelompok2.payload.response.MessageResponse;
import com.example.miniprojecthospitalkelompok2.payload.response.TokenRefreshResponse;
import com.example.miniprojecthospitalkelompok2.repository.RoleRepository;
import com.example.miniprojecthospitalkelompok2.repository.UserRepository;
import com.example.miniprojecthospitalkelompok2.security.jwt.JwtUtils;
import com.example.miniprojecthospitalkelompok2.security.services.RefreshTokenService;
import com.example.miniprojecthospitalkelompok2.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
                userDetails.getUsername(), userDetails.getEmail(), refreshToken.getExpiryDate(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Users users = new Users(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(USER_ENUM.USER_TYPE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "doctor":
                        Role modRole = roleRepository.findByName(USER_ENUM.USER_TYPE_DOCTOR)
                                .orElseThrow(() ->
                                        new RuntimeException("Error: Role is not found."));
                        System.out.println(modRole);
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(USER_ENUM.USER_TYPE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        users.setRole(roles);
        userRepository.save(users);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        Optional<RefreshToken> refreshToken =  refreshTokenService.findByToken(requestRefreshToken);
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUsers)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(((Users) user).getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken, refreshToken.get().getExpiryDate()));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
        refreshTokenService.deleteByUserId(logOutRequest.getUserId());
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }

}