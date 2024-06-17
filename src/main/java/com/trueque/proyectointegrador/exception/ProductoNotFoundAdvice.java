package com.trueque.proyectointegrador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ProductoNotFoundAdvice {

    @ResponseBody //función se debe convertir en JSON y enviar al cliente
    @ExceptionHandler(ProductoNotFoundException.class) //manejará errores del tipo ProductoNotFoundException
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> exceptionHandler(ProductoNotFoundException exception){ //toma un error ProductoNotFoundException y devuelve un mapa con texto
        Map<String, String> errorMap=new HashMap<>(); //nuevo mapa para guardar el mensaje de error
        errorMap.put("errorMessage", exception.getMessage());

        return errorMap;

    }

}
