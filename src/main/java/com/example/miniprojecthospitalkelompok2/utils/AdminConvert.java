package com.example.miniprojecthospitalkelompok2.utils;
import com.example.miniprojecthospitalkelompok2.payload.request.AdminRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminConvert {
    private AdminRequest request;
    private String username;
    private String role;

    public AdminConvert(String username, String role) {
        this.username = username;
        this.role = role;
    }
}
