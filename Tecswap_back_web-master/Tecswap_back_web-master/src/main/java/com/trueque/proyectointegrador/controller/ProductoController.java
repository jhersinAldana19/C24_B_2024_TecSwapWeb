package com.trueque.proyectointegrador.controller;

import com.trueque.proyectointegrador.exception.ProductoNotFoundException;
import com.trueque.proyectointegrador.model.Producto;
import com.trueque.proyectointegrador.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping("/producto")
    Producto newProducto (@RequestBody Producto newProducto){
        return productoRepository.save(newProducto);
    }

    @GetMapping("/productos")
    List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    @GetMapping("/producto/{id}")
    Producto getProductoById(@PathVariable Long id){
        return productoRepository.findById(id)
                .orElseThrow(()->new ProductoNotFoundException(id));
    }

    @PutMapping("/producto/{id}")
    Producto updateProducto(@RequestBody Producto newProducto, @PathVariable Long id){
        return productoRepository.findById(id)
                .map(producto ->{
                    producto.setTitulo(newProducto.getTitulo());
                    producto.setDescripcion(newProducto.getDescripcion());
                    producto.setEstado(newProducto.getEstado());
                    producto.setCantidad(newProducto.getCantidad());
                    producto.setCategoria_id(newProducto.getCategoria_id());
                    producto.setImagen(newProducto.getImagen());
                    return productoRepository.save(producto);
                }).orElseThrow(()->new ProductoNotFoundException(id));
    }

    @DeleteMapping("producto/{id}")
    String deleteProducto(@PathVariable Long id){
        if(!productoRepository.existsById(id)){
            throw new ProductoNotFoundException(id);
        }
        productoRepository.deleteById(id);
        return "Producto con ID "+id+" se elimino correctamente";
    }

    @GetMapping("/productos/search")
    List<Producto> searchProductos(@RequestParam String query) {
        return productoRepository.findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCase(query, query);
    }

}
