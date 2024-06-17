package com.trueque.proyectointegrador.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer { // WebMvcConfigurer nos ayuda a configurar cosas para nuestra aplicación web
    @Override // estamos modificando una función que viene de WebMvcConfigurer
    public void addResourceHandlers(ResourceHandlerRegistry registry) { //función llamada addResourceHandlers que toma algo llamado ResourceHandlerRegistry como entrada
        registry.addResourceHandler("/uploads/**") // cualquier cosa que comience con "/uploads/" debería ser manejada por este recurso
                .addResourceLocations("classpath:/static/uploads/"); //que busque los archivos que coinciden con "/uploads/**" en la carpeta "static/uploads"
    }
}
