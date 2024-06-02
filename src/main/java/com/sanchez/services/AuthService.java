package com.sanchez.services;

import com.sanchez.dtos.SignupRequest;
import com.sanchez.dtos.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupRequest signupRequest);
}
