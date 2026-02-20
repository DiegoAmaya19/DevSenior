package com.devsenior.diego.bibliokeep.service.impl;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import com.devsenior.diego.bibliokeep.mapper.BookMapper;
import com.devsenior.diego.bibliokeep.repository.BookRepository;

public class BookserviceTest {

    @Test
    public void testGetBookById() {

        //Arrange
        var id = 1L;

        var bookRepository = mock(BookRepository.class);
        var bookMapper = mock(BookMapper.class);
        var service = new BookServiceImpl(bookRepository,bookMapper);

        //Act

        var result = service.findById(id, null);

        //Assert

    }
}
