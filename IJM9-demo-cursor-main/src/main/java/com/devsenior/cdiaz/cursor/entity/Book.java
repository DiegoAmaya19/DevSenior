package com.devsenior.cdiaz.cursor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad que representa un libro en la base de datos.
 * 
 * @author DevSenior
 * @version 1.0
 */
@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "edition")
    private Integer edition;

    @Column(name = "publish_year")
    private Integer publishYear;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Book() {
    }

    /**
     * Constructor con todos los parámetros.
     * 
     * @param isbn        el ISBN del libro
     * @param title       el título del libro
     * @param author      el autor del libro
     * @param edition     la edición del libro
     * @param publishYear el año de publicación del libro
     */
    public Book(String isbn, String title, String author, Integer edition, Integer publishYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.publishYear = publishYear;
    }

    /**
     * Obtiene el ISBN del libro.
     * 
     * @return el ISBN del libro
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Establece el ISBN del libro.
     * 
     * @param isbn el ISBN del libro
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene el título del libro.
     * 
     * @return el título del libro
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el título del libro.
     * 
     * @param title el título del libro
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtiene el autor del libro.
     * 
     * @return el autor del libro
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Establece el autor del libro.
     * 
     * @param author el autor del libro
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Obtiene la edición del libro.
     * 
     * @return la edición del libro
     */
    public Integer getEdition() {
        return edition;
    }

    /**
     * Establece la edición del libro.
     * 
     * @param edition la edición del libro
     */
    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    /**
     * Obtiene el año de publicación del libro.
     * 
     * @return el año de publicación del libro
     */
    public Integer getPublishYear() {
        return publishYear;
    }

    /**
     * Establece el año de publicación del libro.
     * 
     * @param publishYear el año de publicación del libro
     */
    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }
}
