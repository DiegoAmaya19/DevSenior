package com.devsenior.cdiaz.cursor.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.devsenior.cdiaz.cursor.dto.BookDTO;
import com.devsenior.cdiaz.cursor.entity.Book;

/**
 * Mapper para convertir entre entidades Book y DTOs BookDTO.
 * 
 * @author DevSenior
 * @version 1.0
 */
@Component
public class BookMapper {

    /**
     * Convierte una entidad Book a un DTO BookDTO.
     * 
     * @param book la entidad Book a convertir
     * @return el DTO BookDTO correspondiente
     */
    public BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }
        return new BookDTO(
                book.getIsbn(),
                book.getTitle(),
                book.getAuthor(),
                book.getEdition(),
                book.getPublishYear());
    }

    /**
     * Convierte un DTO BookDTO a una entidad Book.
     * 
     * @param bookDTO el DTO BookDTO a convertir
     * @return la entidad Book correspondiente
     */
    public Book toEntity(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        }
        return new Book(
                bookDTO.isbn(),
                bookDTO.title(),
                bookDTO.author(),
                bookDTO.edition(),
                bookDTO.publishYear());
    }

    /**
     * Convierte una lista de entidades Book a una lista de DTOs BookDTO.
     * 
     * @param books la lista de entidades Book a convertir
     * @return la lista de DTOs BookDTO correspondientes
     */
    public List<BookDTO> toDTOList(List<Book> books) {
        if (books == null) {
            return null;
        }
        return books.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convierte una lista de DTOs BookDTO a una lista de entidades Book.
     * 
     * @param bookDTOs la lista de DTOs BookDTO a convertir
     * @return la lista de entidades Book correspondientes
     */
    public List<Book> toEntityList(List<BookDTO> bookDTOs) {
        if (bookDTOs == null) {
            return null;
        }
        return bookDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
