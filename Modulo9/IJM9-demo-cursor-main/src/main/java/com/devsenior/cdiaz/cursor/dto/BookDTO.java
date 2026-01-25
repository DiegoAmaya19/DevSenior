package com.devsenior.cdiaz.cursor.dto;

/**
 * DTO (Data Transfer Object) que representa un libro para transferencia de datos.
 * Utiliza Java record para inmutabilidad y simplicidad.
 * 
 * @param isbn        el ISBN del libro
 * @param title       el título del libro
 * @param author      el autor del libro
 * @param edition     la edición del libro
 * @param publishYear el año de publicación del libro
 * 
 * @author DevSenior
 * @version 1.0
 */
public record BookDTO(
        String isbn,
        String title,
        String author,
        Integer edition,
        Integer publishYear) {
}
