package com.trueque.proyectointegrador.repository;

import com.trueque.proyectointegrador.model.SolicitudIntercambio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudIntercambioRepository extends JpaRepository<SolicitudIntercambio, Long> {
    List<SolicitudIntercambio> findByPropietarioId(Long propietarioId);
}