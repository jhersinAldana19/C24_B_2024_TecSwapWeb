package com.trueque.proyectointegrador.exception;

public class ProductoNotFoundException extends RuntimeException{

    public ProductoNotFoundException(Long id){
        super("No se puede conectar con el ID "+id);
    }

}
