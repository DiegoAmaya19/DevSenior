package com.devsenior.cdiaz.cursor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsenior.cdiaz.cursor.entity.Book;

/**
 * Repositorio para la entidad Book que proporciona operaciones de acceso a datos.
 * 
 * @author DevSenior
 * @version 1.0
 */
public interface BookRepository extends JpaRepository<Book, String> {

    /**
     * Busca libros por autor.
     * 
     * @param author el nombre del autor a buscar
     * @return una lista de libros del autor especificado
     */
    List<Book> findByAuthor(String author);

    /**
     * Busca libros cuyo título contenga el texto especificado (búsqueda parcial, case-insensitive).
     * 
     * @param title el texto a buscar en el título
     * @return una lista de libros cuyo título contenga el texto especificado
     */
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Book> findByTitleContaining(@Param("title") String title);
}
