package com.trueque.proyectointegrador.service;

import com.trueque.proyectointegrador.model.Roles;
import com.trueque.proyectointegrador.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    public Roles findByTipo(String tipo) {
        return rolesRepository.findByTipo(tipo);
    }
}
