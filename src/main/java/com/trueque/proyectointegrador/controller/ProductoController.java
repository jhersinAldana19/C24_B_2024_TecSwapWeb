package com.trueque.proyectointegrador.controller;

import com.trueque.proyectointegrador.exception.ProductoNotFoundException;
import com.trueque.proyectointegrador.model.Categoria;
import com.trueque.proyectointegrador.model.Producto;
import com.trueque.proyectointegrador.model.Usuario;
import com.trueque.proyectointegrador.repository.CategoriaRepository;
import com.trueque.proyectointegrador.repository.ProductoRepository;
import com.trueque.proyectointegrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000") //reciba solicitudes desde http://localhost:3000
@RestController //manejará solicitudes HTTP y devolverá respuestas JSON
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    // son como bases de datos en miniatura donde guardamos nuestros productos y usuarios

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/user/products")
    public List<Producto> getUserProducts(Authentication authentication) {
        String email = authentication.getName();  // Obtenemos el email del usuario logueado
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();  // Buscamos al usuario por email
        return productoRepository.findByUsuarioId(usuario.getId());  // Obtenemos los productos del usuario por su ID
    }

    @PostMapping("/producto")
    public Producto newProducto(@RequestBody Producto newProducto, Authentication authentication) {
        String username = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow();
        newProducto.setUsuario(usuario);

        // Buscar la categoría por ID y establecerla
        Categoria categoria = categoriaRepository.findById(newProducto.getCategoria().getId())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no válida"));
        newProducto.setCategoria(categoria);

        return productoRepository.save(newProducto);
    }

    @GetMapping("/productos")
    public List<Producto> getAllProductos() { //devuelve una lista de todos los productos
        return productoRepository.findAll(); //devuelve todos los productos
    }

    @GetMapping("/producto/{id}") //solicitudes GET a /producto/{id}, donde {id} es un valor variable
    public Producto getProductoById(@PathVariable Long id) { //devuelve un producto por su ID
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
    } //buscamos el producto en la base de datos por su ID y lo devolvemos. Si no se encuentra, lanzamos una excepción

    @PutMapping("/producto/{id}")
    public Producto updateProducto(@RequestBody Producto newProducto, @PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow();

        return productoRepository.findById(id)
                .map(producto -> {
                    if (!producto.getUsuario().getEmail().equals(username)) {
                        throw new IllegalArgumentException("No tienes permiso para actualizar este producto");
                    }
                    producto.setTitulo(newProducto.getTitulo());
                    producto.setDescripcion(newProducto.getDescripcion());
                    producto.setEstado(newProducto.getEstado());
                    producto.setCantidad(newProducto.getCantidad());
                    producto.setImagen(newProducto.getImagen());

                    // Buscar la categoría por ID y establecerla
                    Categoria categoria = categoriaRepository.findById(newProducto.getCategoria().getId())
                            .orElseThrow(() -> new IllegalArgumentException("Categoría no válida"));
                    producto.setCategoria(categoria);

                    return productoRepository.save(producto);
                }).orElseThrow(() -> new ProductoNotFoundException(id));
    }

    @DeleteMapping("/producto/{id}")
    public String deleteProducto(@PathVariable Long id, Authentication authentication) { //elimina un producto por su ID
        String username = authentication.getName(); //nombre de usuario del usuario autenticado
        Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow(); //Buscamos al usuario en la base de datos usando su email
        // buscamos el producto por su ID. Si no se encuentra, lanzamos una excepción
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        // Si el usuario autenticado no es el propietario del producto, lanzamos una excepción
        if (!producto.getUsuario().getName().equals(username)) {
            throw new IllegalArgumentException("No tienes permiso para eliminar este producto");
        }

        productoRepository.deleteById(id);//Eliminamos el producto de la base de datos
        return "Producto con ID " + id + " se eliminó correctamente";
    }

    @GetMapping("/productos/search") //solicitudes GET a /productos/search
    public List<Producto> searchProductos(@RequestParam String query) { // busca productos por título o descripción
        return productoRepository.findByTituloContainingIgnoreCaseOrDescripcionContainingIgnoreCase(query, query);
    } //evolvemos una lista de productos que contienen la palabra clave en el título o la descripción, sin importar mayúsculas o minúsculas
}

