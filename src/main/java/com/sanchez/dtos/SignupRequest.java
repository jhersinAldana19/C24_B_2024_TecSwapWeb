package com.sanchez.dtos;

import lombok.Data;

@Data
public class SignupRequest {

    private String name;
    private String email;
    private String phone;
    private String password;
    private String lastname;
    private String imagen;
    private String carrera;

}
