package com.trueque.proyectointegrador.repository;

import com.trueque.proyectointegrador.model.Categoria;
import com.trueque.proyectointegrador.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCase(String titulo, String descripcion);

    List<Producto> findByUsuarioId(Long usuarioId);

    List<Producto> findByCategoria(Categoria categoria);
}
