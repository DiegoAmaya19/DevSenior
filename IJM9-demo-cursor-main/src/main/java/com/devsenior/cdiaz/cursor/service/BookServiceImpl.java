package com.devsenior.cdiaz.cursor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsenior.cdiaz.cursor.dto.BookDTO;
import com.devsenior.cdiaz.cursor.entity.Book;
import com.devsenior.cdiaz.cursor.exception.BookNotFoundException;
import com.devsenior.cdiaz.cursor.mapper.BookMapper;
import com.devsenior.cdiaz.cursor.repository.BookRepository;

/**
 * Implementación del servicio para gestionar operaciones relacionadas con libros.
 * 
 * @author DevSenior
 * @version 1.0
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    /**
     * Constructor que inyecta las dependencias necesarias.
     * 
     * @param bookRepository el repositorio de libros
     * @param bookMapper     el mapper para conversión entre entidades y DTOs
     */
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> findAll() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toDTOList(books);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public BookDTO findByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException("Libro con ISBN " + isbn + " no encontrado"));
        return bookMapper.toDTO(book);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BookDTO save(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByIsbn(String isbn) {
        if (!bookRepository.existsById(isbn)) {
            throw new BookNotFoundException("Libro con ISBN " + isbn + " no encontrado");
        }
        bookRepository.deleteById(isbn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> findByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        return bookMapper.toDTOList(books);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> findByTitleContaining(String title) {
        List<Book> books = bookRepository.findByTitleContaining(title);
        return bookMapper.toDTOList(books);
    }
}
