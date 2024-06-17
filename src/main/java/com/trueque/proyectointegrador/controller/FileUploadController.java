package com.trueque.proyectointegrador.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin("http://localhost:3000") //permita solicitudes desde http://localhost:3000
@RestController //maneja solicitudes web y devuelve datos
public class FileUploadController{

    @PostMapping("/upload") //manejará las solicitudes que envían archivos a la ruta /upload
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) { //toma un archivo como entrada y devuelve una respuesta HTTP
        try {
            // Define la ruta donde quieres guardar los archivos
            String directoryPath = "C:\\uploads";
            File directory = new File(directoryPath);

            // Si la ruta no existe, crearla
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Construir la ruta completa del archivo
            String filePath = directoryPath + "\\" + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            // Devolver la URL del archivo
            String relativeFilePath = "/uploads/" + file.getOriginalFilename(); // Ruta relativa
            Map<String, String> response = new HashMap<>();
            response.put("url", relativeFilePath);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al cargar el archivo: " + e.getMessage());
        }
    }
}
