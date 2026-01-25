package com.devsenior.cdiaz.cursor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global de excepciones para todos los controladores REST.
 * 
 * @author DevSenior
 * @version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja la excepción BookNotFoundException.
     * 
     * @param ex la excepción lanzada
     * @return un mapa con el mensaje de error
     */
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleBookNotFoundException(BookNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Libro no encontrado");
        errorResponse.put("message", ex.getMessage());
        return errorResponse;
    }

    /**
     * Maneja excepciones genéricas no controladas.
     * 
     * @param ex la excepción lanzada
     * @return un mapa con el mensaje de error
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleGenericException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Error interno del servidor");
        errorResponse.put("message", ex.getMessage());
        return errorResponse;
    }
}
