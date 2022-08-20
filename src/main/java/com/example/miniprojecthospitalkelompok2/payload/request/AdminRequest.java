package com.example.miniprojecthospitalkelompok2.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AdminRequest {

    private Long user_id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    private String full_name;

    @NotBlank
    private String age;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 5, max = 40)
    private String password;

    @NotBlank
    private String gender;

    @NotBlank
    private String address;

    private String role;
}
