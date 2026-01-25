package com.devsenior.cdiaz.cursor.service;

import java.util.List;

import com.devsenior.cdiaz.cursor.dto.BookDTO;

/**
 * Interfaz del servicio para gestionar operaciones relacionadas con libros.
 * 
 * @author DevSenior
 * @version 1.0
 */
public interface BookService {

    /**
     * Obtiene todos los libros.
     * 
     * @return una lista con todos los libros
     */
    List<BookDTO> findAll();

    /**
     * Busca un libro por su ISBN.
     * 
     * @param isbn el ISBN del libro a buscar
     * @return el DTO del libro encontrado, o null si no existe
     */
    BookDTO findByIsbn(String isbn);

    /**
     * Guarda un nuevo libro o actualiza uno existente.
     * 
     * @param bookDTO el DTO del libro a guardar o actualizar
     * @return el DTO del libro guardado
     */
    BookDTO save(BookDTO bookDTO);

    /**
     * Elimina un libro por su ISBN.
     * 
     * @param isbn el ISBN del libro a eliminar
     */
    void deleteByIsbn(String isbn);

    /**
     * Busca libros por autor.
     * 
     * @param author el nombre del autor a buscar
     * @return una lista de libros del autor especificado
     */
    List<BookDTO> findByAuthor(String author);

    /**
     * Busca libros cuyo título contenga el texto especificado (búsqueda parcial).
     * 
     * @param title el texto a buscar en el título
     * @return una lista de libros cuyo título contenga el texto especificado
     */
    List<BookDTO> findByTitleContaining(String title);
}
