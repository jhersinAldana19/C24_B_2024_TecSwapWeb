package com.trueque.proyectointegrador.controller;

import com.trueque.proyectointegrador.model.Carrera;
import com.trueque.proyectointegrador.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
public class CarreraController {

    @Autowired
    private CarreraRepository carreraRepository;

    @GetMapping("/carreras")
    public ResponseEntity<List<Carrera>> getAllCarreras() {
        List<Carrera> carreras = carreraRepository.findAll();
        return ResponseEntity.ok(carreras);
    }
}
