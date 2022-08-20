package com.example.miniprojecthospitalkelompok2.payload.response;

import com.example.miniprojecthospitalkelompok2.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class IgnoreResponse {
    @JsonIgnoreProperties("password")
    public class GetUsers extends Users {}
}
