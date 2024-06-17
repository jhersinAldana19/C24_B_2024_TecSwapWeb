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
@RequestMapping("/api") //solicitudes que empiecen con /api se manejarán en esta clase.
public class UsuarioController {

    private final UsuarioService usuarioService; //servicio que usamos para trabajar con usuarios
    private final PasswordEncoder passwordEncoder; //herramienta que usamos para codificar contraseñas

    @Autowired //queremos que inyecte automáticamente estas dependencias cuando se crea la clase
    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) { //se ejecuta cuando creamos un nuevo UsuarioController y establece las dependencias
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register") //solicitudes POST a /api/register
    public ResponseEntity<?> registerUser(@RequestBody Usuario usuario) { //registra un nuevo usuario
        try {
            if (usuarioService.findByEmail(usuario.getEmail()) != null) { //comprueba si ya existe un usuario con el email proporcionado
                return ResponseEntity.badRequest().body("El usuario ya existe");
            }
            return ResponseEntity.ok(usuarioService.saveUsuario(usuario));//Si no hay errores, guarda el nuevo usuario y devuelve una respuesta exitosa
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
