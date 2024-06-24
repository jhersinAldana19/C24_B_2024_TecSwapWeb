package com.trueque.proyectointegrador.repository;

import com.trueque.proyectointegrador.model.SolicitudIntercambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitudIntercambioRepository extends JpaRepository<SolicitudIntercambio, Long> {
    List<SolicitudIntercambio> findByPropietarioId(Long propietarioId);
    List<SolicitudIntercambio> findByPropietarioIdAndRespondidaFalse(Long propietarioId);

    @Query("SELECT s FROM SolicitudIntercambio s WHERE s.propietario.id = :propietarioId ORDER BY s.fecha DESC")
    List<SolicitudIntercambio> findByPropietarioIdOrderedByFechaDesc(@Param("propietarioId") Long propietarioId);
}