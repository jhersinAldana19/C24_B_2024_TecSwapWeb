package com.trueque.proyectointegrador.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration //etiqueta que le dice a Spring que esta clase tiene configuraciones importantes
public class PasswordEncoderConfig {

    @Bean //etiqueta que le dice a Spring que esta función (método) crea algo que podemos usar en otras partes de nuestra aplicación
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //creando un nuevo objeto que nos ayuda a guardar contraseñas de manera segura
    }
}
