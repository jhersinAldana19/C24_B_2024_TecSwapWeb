package com.trueque.proyectointegrador.repository;

import com.trueque.proyectointegrador.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {
}