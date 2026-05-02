package com.example.db.library.service;

import com.example.db.library.entity.Author;
import com.example.db.library.entity.Book;
import com.example.db.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;
    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author("Test Author", "Test Bio");
        author.setId(1L);

        book = new Book("Test Title", "123456789", author);
        book.setId(1L);
    }

    @Test
    void testFindAllBooksWithAuthors() {
        when(bookRepository.findAllBooksWithAuthors()).thenReturn(Arrays.asList(book));

        List<Book> books = bookService.findAllBooksWithAuthors();

        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals("Test Title", books.get(0).getTitle());
        verify(bookRepository, times(1)).findAllBooksWithAuthors();
    }

    @Test
    void testFindById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> foundBook = bookService.findById(1L);

        assertTrue(foundBook.isPresent());
        assertEquals("Test Title", foundBook.get().getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveBookSuccess() {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book savedBook = bookService.save(book);

        assertNotNull(savedBook);
        assertEquals("Test Title", savedBook.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testSaveBookIntegrityViolation() {
        when(bookRepository.save(any(Book.class))).thenThrow(DataIntegrityViolationException.class);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookService.save(book);
        });

        assertTrue(exception.getMessage().contains("Error saving book"));
        verify(bookRepository, times(1)).save(book);
    }
}
