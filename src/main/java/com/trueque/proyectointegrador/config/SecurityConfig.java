package com.trueque.proyectointegrador.config;

import com.trueque.proyectointegrador.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity //etiqueta que le dice a Spring que esta clase se encarga de la seguridad de nuestra app
public class SecurityConfig {

    private final UsuarioService usuarioService; // referencia a nuestro UsuarioService
    private final PasswordEncoder passwordEncoder; // referencia a nuestro PasswordEncoder

    public SecurityConfig(UsuarioService usuarioService, PasswordEncoder passwordEncoder) { // un objeto de SecurityConfig, necesitamos pasarle un UsuarioService y un PasswordEncoder
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //función llamada securityFilterChain
        http.csrf(csrf -> csrf.disable()) // desactivamos una protección llamada CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/register", "/api/login", "/productos").permitAll() // permite que cualquiera pueda acceder a estas rutas
                        .anyRequest().authenticated() // para cualquier otra ruta, el usuario debe estar autenticado
                )
                .httpBasic(withDefaults())
                .formLogin(formLogin -> formLogin.disable()) // Deshabilita el formulario de login
                .logout(logout -> logout.permitAll()); // construimos y devolvemos nuestra configuración de seguridad
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class); //objeto para construir nuestro AuthenticationManager
        authenticationManagerBuilder
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder); //authenticationManagerBuilder que use nuestro usuarioService para obtener detalles de los usuarios y nuestro passwordEncoder para codificar las contraseñas.
        return authenticationManagerBuilder.build(); //devolvemos nuestro AuthenticationManager
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/api/register", "/api/login", "/productos", "/api/carreras", "/categorias");
    } //decimos a Spring que ignore la seguridad para estas rutas

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) { // permitir que pueda hacer peticiones a nuestro servidor
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}