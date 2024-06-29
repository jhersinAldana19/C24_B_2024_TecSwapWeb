package com.trueque.proyectointegrador.controller;

import com.trueque.proyectointegrador.model.Carrera;
import com.trueque.proyectointegrador.model.Usuario;
import com.trueque.proyectointegrador.service.CarreraService;
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
    private final CarreraService carreraService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, CarreraService carreraService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.carreraService = carreraService;
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

            Long carreraId = Long.valueOf((Integer) payload.get("carrera_id"));
            Carrera carrera = carreraService.findById(carreraId);
            if (carrera == null) {
                return ResponseEntity.badRequest().body("Carrera no encontrada");
            }
            usuario.setCarrera(carrera);

            return ResponseEntity.ok(usuarioService.saveUsuario(usuario));
        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().body("Email ya está registrado.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar el usuario: " + e.getMessage());
        }
    }

    @PostMapping("/login")//solicitudes POST a /api/login
    public ResponseEntity<?> loginUser(@RequestBody Usuario usuario) { //Esta función maneja el inicio de sesión de un usuario
        Usuario user = usuarioService.findByEmail(usuario.getEmail()); //Busca un usuario por su email
        if (user == null || !passwordEncoder.matches(usuario.getPassword(), user.getPassword())) { //si el usuario existe y si la contraseña proporcionada coincide con la contraseña almacenada
            return ResponseEntity.badRequest().body("Usuario o contraseña incorrecta");
        }// Si el usuario no existe o la contraseña no coincide, devuelve un error diciendo "Usuario o contraseña incorrecta"

        Map<String, Object> response = new HashMap<>(); //Crea un nuevo mapa para la respuesta
        response.put("name", user.getName()); //Añade el nombre del usuario al mapa de la respuesta
        return ResponseEntity.ok(response); //Devuelve una respuesta exitosa con el nombre del usuario
    }



}