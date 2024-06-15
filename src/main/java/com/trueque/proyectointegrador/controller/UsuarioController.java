package com.trueque.proyectointegrador.controller;

import com.trueque.proyectointegrador.model.Usuario;
import com.trueque.proyectointegrador.service.UsuarioService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Usuario usuario) {
        try {
            if (usuarioService.findByEmail(usuario.getEmail()) != null) {
                return ResponseEntity.badRequest().body("El usuario ya existe");
            }
            return ResponseEntity.ok(usuarioService.saveUsuario(usuario));
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().body("Email ya está registrado.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar el usuario: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Usuario usuario) {
        Usuario user = usuarioService.findByEmail(usuario.getEmail());
        if (user == null || !passwordEncoder.matches(usuario.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Usuario o contraseña incorrecta");
        }
        // Aquí puedes generar un token de sesión si es necesario
        Map<String, Object> response = new HashMap<>();
        response.put("name", user.getName());
        return ResponseEntity.ok(response);
    }
}
