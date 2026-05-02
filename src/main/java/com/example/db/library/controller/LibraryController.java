package com.example.db.library.controller;

import com.example.db.library.entity.Author;
import com.example.db.library.entity.Book;
import com.example.db.library.service.AuthorService;
import com.example.db.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class LibraryController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public LibraryController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    // READ operation - List all books
    @GetMapping({"/", "/books"})
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooksWithAuthors());
        return "list";
    }

    // CREATE operation - Show form
    @GetMapping("/addBook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        return "addBook";
    }

    // CREATE operation - Submit form
    @PostMapping("/addBook")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            return "addBook";
        }
        try {
            bookService.save(book);
            redirectAttributes.addFlashAttribute("successMessage", "Book added successfully!");
            return "redirect:/books";
        } catch (RuntimeException e) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("errorMessage", e.getMessage());
            return "addBook";
        }
    }

    // UPDATE operation - Show form
    @GetMapping("/editBook/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        return "editBook";
    }

    // UPDATE operation - Submit form
    @PostMapping("/editBook/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            book.setId(id);
            model.addAttribute("authors", authorService.findAll());
            return "editBook";
        }
        try {
            book.setId(id);
            bookService.save(book);
            redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully!");
            return "redirect:/books";
        } catch (RuntimeException e) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("errorMessage", e.getMessage());
            return "editBook";
        }
    }

    // Separate forms for Author if needed
    @GetMapping("/addAuthor")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "addAuthor";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@Valid @ModelAttribute("author") Author author, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "addAuthor";
        }
        authorService.save(author);
        redirectAttributes.addFlashAttribute("successMessage", "Author added successfully!");
        return "redirect:/books";
    }
}
