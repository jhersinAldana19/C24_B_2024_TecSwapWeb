package com.trueque.proyectointegrador.service;

import com.trueque.proyectointegrador.model.Carrera;
import com.trueque.proyectointegrador.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarreraService {

    private final CarreraRepository carreraRepository;

    @Autowired
    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    public Carrera findById(Long id) {
        return carreraRepository.findById(id).orElse(null);
    }
}