package com.trueque.proyectointegrador.repository;

import com.trueque.proyectointegrador.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByTipo(String tipo);
}