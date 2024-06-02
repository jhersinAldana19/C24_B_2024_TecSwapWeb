package com.sanchez.services;

import com.sanchez.dtos.SignupRequest;
import com.sanchez.dtos.UserDTO;
import com.sanchez.models.User;
import com.sanchez.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SignupRequest signupRequest) {
        // Verificar si el correo ya existe
        if (userRepository.findFirstByEmail(signupRequest.getEmail()) != null) {
            throw new IllegalArgumentException("El correo electrónico ya está en uso");
        }

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPhone(signupRequest.getPhone());
        user.setLastname((signupRequest.getLastname()));
        user.setCarrera((signupRequest.getCarrera()));
        user.setImagen((signupRequest.getImagen()));
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));

        User createdUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(createdUser.getId());
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setName(createdUser.getName());
        userDTO.setPhone(createdUser.getPhone());
        user.setLastname((signupRequest.getLastname()));
        user.setCarrera((signupRequest.getCarrera()));
        user.setImagen((signupRequest.getImagen()));

        return userDTO;
    }
}
