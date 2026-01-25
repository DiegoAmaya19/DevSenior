package com.devsenior.cdiaz.cursor.exception;

/**
 * Excepción lanzada cuando no se encuentra un libro en el sistema.
 * 
 * @author DevSenior
 * @version 1.0
 */
public class BookNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor que crea una excepción con un mensaje.
     * 
     * @param message el mensaje de error
     */
    public BookNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor que crea una excepción con un mensaje y una causa.
     * 
     * @param message el mensaje de error
     * @param cause   la causa de la excepción
     */
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
