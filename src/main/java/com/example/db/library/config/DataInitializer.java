package com.example.db.library.config;

import com.example.db.library.entity.Author;
import com.example.db.library.entity.Book;
import com.example.db.library.repository.AuthorRepository;
import com.example.db.library.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(AuthorRepository authorRepository, BookRepository bookRepository) {
        return args -> {
            if (authorRepository.count() == 0) {
                // Create 10 Authors
                Author a1 = new Author("J.K. Rowling", "British author, best known for the Harry Potter series.");
                Author a2 = new Author("George R.R. Martin", "American novelist, author of A Song of Ice and Fire.");
                Author a3 = new Author("J.R.R. Tolkien", "English writer, poet, philologist, and academic.");
                Author a4 = new Author("Agatha Christie", "English writer known for her 66 detective novels.");
                Author a5 = new Author("Stephen King", "American author of horror, supernatural fiction, suspense.");
                Author a6 = new Author("Isaac Asimov", "American writer and professor of biochemistry.");
                Author a7 = new Author("Arthur Conan Doyle", "British writer, who created the character Sherlock Holmes.");
                Author a8 = new Author("Jane Austen", "English novelist known primarily for her six major novels.");
                Author a9 = new Author("Mark Twain", "American writer, humorist, entrepreneur, publisher, and lecturer.");
                Author a10 = new Author("Charles Dickens", "English writer and social critic.");

                authorRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10));

                // Create 10 Books
                Book b1 = new Book("Harry Potter and the Sorcerer's Stone", "978-0590353427", a1);
                Book b2 = new Book("A Game of Thrones", "978-0553103540", a2);
                Book b3 = new Book("The Fellowship of the Ring", "978-0618346257", a3);
                Book b4 = new Book("And Then There Were None", "978-0062073488", a4);
                Book b5 = new Book("The Shining", "978-0307743657", a5);
                Book b6 = new Book("Foundation", "978-0553293357", a6);
                Book b7 = new Book("The Adventures of Sherlock Holmes", "978-0199536955", a7);
                Book b8 = new Book("Pride and Prejudice", "978-0141439518", a8);
                Book b9 = new Book("The Adventures of Tom Sawyer", "978-0143039563", a9);
                Book b10 = new Book("A Tale of Two Cities", "978-0141439600", a10);

                bookRepository.saveAll(Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10));
            }
        };
    }
}
