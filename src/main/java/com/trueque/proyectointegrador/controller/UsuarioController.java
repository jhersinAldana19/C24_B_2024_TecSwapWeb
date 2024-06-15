package com.trueque.proyectointegrador.controller;

import com.trueque.proyectointegrador.model.Usuario;
import com.trueque.proyectointegrador.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


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
        if (usuarioService.findByEmail(usuario.getName()) != null) {
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }
        return ResponseEntity.ok(usuarioService.saveUsuario(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Usuario usuario) {
        Usuario user = usuarioService.findByEmail(usuario.getEmail());
        if (user == null || !passwordEncoder.matches(usuario.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Usuario o contraseña incorrecta");
        }
        return ResponseEntity.ok("Login successful");
    }
}
