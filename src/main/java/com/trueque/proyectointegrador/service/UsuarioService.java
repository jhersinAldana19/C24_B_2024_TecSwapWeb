package com.trueque.proyectointegrador.service;

import com.trueque.proyectointegrador.model.Usuario;
import com.trueque.proyectointegrador.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service //hace cosas importantes en el programa
public class UsuarioService implements UserDetailsService { //puede hacer cosas relacionadas con la seguridad de los usuarios

    private final UsuarioRepository usuarioRepository; //trabajar con usuarios
    private final PasswordEncoder passwordEncoder; //codificar contraseña

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    } //Se ejecuta cuando creamos un nuevo UsuarioService y establece las dependencias

    @Transactional //asegura que todas las operaciones dentro de esta función se completen correctamente o no se hagan en absoluto
    public Usuario saveUsuario(Usuario usuario) { //guarda un nuevo usuario en la base de datos
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); //codifica la contraseña del usuario antes de guardarla
        return usuarioRepository.save(usuario); //devuelve el usuario guardado
    }

    public Usuario findByEmail(String email) { //busca un usuario por su email
        return usuarioRepository.findByEmail(email).orElse(null); //null si no encuentra ninguno
    }

    @Override //modificando una función de una clase superior
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //carga los detalles del usuario por su email y puede lanzar una excepción si no encuentra al usuario
        //Busca al usuario por su email. Si no lo encuentra, lanza una excepción diciendo "Usuario no encontrado: " + email
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));
        //construye un objeto de usuario con su email, contraseña y rol de "USER"
        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }
}