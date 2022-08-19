package com.example.miniprojecthospitalkelompok2.dto;

import lombok.Data;

@Data
public class RequestAccess {

    private long userId;
    private String userName;
    private String email;
    private String userType;
}
