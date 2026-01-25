package com.devsenior.cdiaz.cursor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.cdiaz.cursor.dto.BookDTO;
import com.devsenior.cdiaz.cursor.service.BookService;

/**
 * Controlador REST para gestionar operaciones relacionadas con libros.
 * 
 * @author DevSenior
 * @version 1.0
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    /**
     * Constructor que inyecta el servicio de libros.
     * 
     * @param bookService el servicio de libros
     */
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Obtiene todos los libros.
     * 
     * @return una lista con todos los libros
     */
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.findAll();
    }

    /**
     * Obtiene un libro por su ISBN.
     * 
     * @param isbn el ISBN del libro a buscar
     * @return el libro encontrado
     * @throws com.devsenior.cdiaz.cursor.exception.BookNotFoundException si el libro no existe
     */
    @GetMapping("/{isbn}")
    public BookDTO getBookByIsbn(@PathVariable String isbn) {
        return bookService.findByIsbn(isbn);
    }

    /**
     * Crea un nuevo libro.
     * 
     * @param bookDTO el DTO del libro a crear
     * @return el libro creado
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        return bookService.save(bookDTO);
    }

    /**
     * Actualiza un libro existente.
     * 
     * @param isbn    el ISBN del libro a actualizar
     * @param bookDTO el DTO con los nuevos datos del libro
     * @return el libro actualizado
     * @throws com.devsenior.cdiaz.cursor.exception.BookNotFoundException si el libro no existe
     */
    @PutMapping("/{isbn}")
    public BookDTO updateBook(@PathVariable String isbn, @RequestBody BookDTO bookDTO) {
        // Verificar que el libro existe (lanzará excepción si no existe)
        bookService.findByIsbn(isbn);
        // Asegurar que el ISBN del path coincida con el del body
        BookDTO updatedBookDTO = new BookDTO(isbn, bookDTO.title(), bookDTO.author(), 
                                             bookDTO.edition(), bookDTO.publishYear());
        return bookService.save(updatedBookDTO);
    }

    /**
     * Elimina un libro por su ISBN.
     * 
     * @param isbn el ISBN del libro a eliminar
     * @throws com.devsenior.cdiaz.cursor.exception.BookNotFoundException si el libro no existe
     */
    @DeleteMapping("/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String isbn) {
        bookService.deleteByIsbn(isbn);
    }

    /**
     * Busca libros por autor.
     * 
     * @param author el nombre del autor a buscar
     * @return una lista de libros del autor especificado
     */
    @GetMapping("/search/author")
    public List<BookDTO> getBooksByAuthor(@RequestParam String author) {
        return bookService.findByAuthor(author);
    }

    /**
     * Busca libros cuyo título contenga el texto especificado (búsqueda parcial).
     * 
     * @param title el texto a buscar en el título
     * @return una lista de libros cuyo título contenga el texto especificado
     */
    @GetMapping("/search/title")
    public List<BookDTO> getBooksByTitleContaining(@RequestParam String title) {
        return bookService.findByTitleContaining(title);
    }
}
