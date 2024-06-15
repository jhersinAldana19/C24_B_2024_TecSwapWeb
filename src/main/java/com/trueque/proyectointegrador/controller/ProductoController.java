package com.trueque.proyectointegrador.controller;

import com.trueque.proyectointegrador.exception.ProductoNotFoundException;
import com.trueque.proyectointegrador.model.Producto;
import com.trueque.proyectointegrador.model.Usuario;
import com.trueque.proyectointegrador.repository.ProductoRepository;
import com.trueque.proyectointegrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/producto")
    Producto newProducto(@RequestBody Producto newProducto, Authentication authentication) {
        String username = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow();
        newProducto.setUsuario(usuario);
        return productoRepository.save(newProducto);
    }

    @GetMapping("/productos")
    List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/producto/{id}")
    Producto getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }

    @PutMapping("/producto/{id}")
    Producto updateProducto(@RequestBody Producto newProducto, @PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow();

        return productoRepository.findById(id)
                .map(producto -> {
                    if (!producto.getUsuario().getName().equals(username)) {
                        throw new IllegalArgumentException("No tienes permiso para actualizar este producto");
                    }
                    producto.setTitulo(newProducto.getTitulo());
                    producto.setDescripcion(newProducto.getDescripcion());
                    producto.setEstado(newProducto.getEstado());
                    producto.setCantidad(newProducto.getCantidad());
                    producto.setCategoria_id(newProducto.getCategoria_id());
                    producto.setImagen(newProducto.getImagen());
                    return productoRepository.save(producto);
                }).orElseThrow(() -> new ProductoNotFoundException(id));
    }

    @DeleteMapping("/producto/{id}")
    String deleteProducto(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow();

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        if (!producto.getUsuario().getName().equals(username)) {
            throw new IllegalArgumentException("No tienes permiso para eliminar este producto");
        }

        productoRepository.deleteById(id);
        return "Producto con ID " + id + " se eliminó correctamente";
    }

    @GetMapping("/productos/search")
    List<Producto> searchProductos(@RequestParam String query) {
        return productoRepository.findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCase(query, query);
    }
}

