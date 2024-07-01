package com.trueque.proyectointegrador.controller;

import com.trueque.proyectointegrador.model.Carrera;
import com.trueque.proyectointegrador.model.Roles;
import com.trueque.proyectointegrador.model.Usuario;
import com.trueque.proyectointegrador.repository.UsuarioRepository;
import com.trueque.proyectointegrador.service.CarreraService;
import com.trueque.proyectointegrador.service.RolesService;
import com.trueque.proyectointegrador.service.UsuarioService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final UsuarioService usuarioService;
    private final CarreraService carreraService;
    private final RolesService rolesService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, CarreraService carreraService, RolesService rolesService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.carreraService = carreraService;
        this.rolesService = rolesService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, Object> payload) {
        try {
            String email = (String) payload.get("email");
            if (usuarioService.findByEmail(email) != null) {
                return ResponseEntity.badRequest().body("El usuario ya existe");
            }

            Usuario usuario = new Usuario();
            usuario.setName((String) payload.get("name"));
            usuario.setLastname((String) payload.get("lastname"));
            usuario.setPhone((String) payload.get("phone"));
            usuario.setEmail(email);
            usuario.setPassword((String) payload.get("password"));
            usuario.setImagen((String) payload.get("imagen"));

            Object carreraIdObj = payload.get("carrera_id");
            if (carreraIdObj == null) {
                return ResponseEntity.badRequest().body("Carrera ID es nulo");
            }

            Long carreraId;
            if (carreraIdObj instanceof Integer) {
                carreraId = ((Integer) carreraIdObj).longValue();
            } else if (carreraIdObj instanceof String) {
                carreraId = Long.parseLong((String) carreraIdObj);
            } else {
                return ResponseEntity.badRequest().body("Carrera ID es de un tipo no soportado");
            }

            Carrera carrera = carreraService.findById(carreraId);
            if (carrera == null) {
                return ResponseEntity.badRequest().body("Carrera no encontrada");
            }
            usuario.setCarrera(carrera);

            // Asigna el rol de "usuario"
            Roles rolUsuario = rolesService.findByTipo("usuario");
            usuario.setRoles(rolUsuario);

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

        Map<String, Object> response = new HashMap<>();
        response.put("name", user.getName());
        response.put("email", user.getEmail());
        response.put("id", user.getId());
        response.put("roles", user.getRoles().getTipo()); // Devuelve el rol del usuario

        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(Authentication authentication) {
        try {
            if (authentication == null || !authentication.isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }
            String email = authentication.getName();
            Usuario usuario = usuarioService.findByEmail(email);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching user data: " + e.getMessage());
        }
    }

    @PostMapping("/profile/upload")
    public ResponseEntity<?> uploadProfileImage(Authentication authentication, @RequestParam("imagen") MultipartFile imagen) {
        if (imagen.isEmpty()) {
            return ResponseEntity.badRequest().body("No se ha proporcionado ningún archivo");
        }

        try {
            String email = authentication.getName();
            Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            String directoryPath = "C:/uploads";
            File directory = new File(directoryPath);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = imagen.getOriginalFilename();
            Path filePath = Paths.get(directoryPath, fileName);
            Files.copy(imagen.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            usuario.setImagen(fileName);
            usuarioRepository.save(usuario);

            return ResponseEntity.ok(usuario);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir el archivo: " + e.getMessage());
        }
    }

    @PostMapping("/profile/update")
    public ResponseEntity<?> updateUserProfile(@RequestBody Usuario usuario, Authentication authentication) {
        try {
            String email = authentication.getName();
            Usuario currentUser = usuarioService.findByEmail(email);
            if (currentUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }
            currentUser.setName(usuario.getName());
            currentUser.setLastname(usuario.getLastname());
            currentUser.setPhone(usuario.getPhone());
            usuarioService.saveUsuario(currentUser);
            return ResponseEntity.ok(currentUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user data");
        }
    }
}
