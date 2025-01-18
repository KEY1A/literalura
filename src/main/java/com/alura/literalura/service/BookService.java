package com.alura.literalura.service;

import com.alura.literalura.dto.GutendexBookDTO;
import com.alura.literalura.dto.GutendexResponseDTO;
import com.alura.literalura.exception.BookAlreadyExistsException;
import com.alura.literalura.exception.BookNotFoundException;
import com.alura.literalura.model.Author;
import com.alura.literalura.model.Book;
import com.alura.literalura.repository.AuthorRepository;
import com.alura.literalura.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GutendexClient gutendexClient;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, GutendexClient gutendexClient) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.gutendexClient = gutendexClient;
    }

    public Book searchAndSaveBook(String title) {
        Optional<Book> existingBook = bookRepository.findByTitleContainingIgnoreCase(title);
        if (existingBook.isPresent()) {
            throw new BookAlreadyExistsException("El libro ya existe en la base de datos");
        }

        GutendexResponseDTO response = gutendexClient.searchBook(title);
        if (response.getResults().isEmpty()) {
            throw new BookNotFoundException("Libro no encontrado");
        }

        return saveBookFromDTO(response.getResults().get(0));
    }

    private Book saveBookFromDTO(GutendexBookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setLanguage(dto.getLanguages().get(0));
        book.setDownloads(dto.getDownload_count());

        if (!dto.getAuthors().isEmpty()) {
            Author author = new Author();
            author.setName(dto.getAuthors().get(0).getName());
            author.setBirthYear(dto.getAuthors().get(0).getBirth_year());
            author.setDeathYear(dto.getAuthors().get(0).getDeath_year());
            book.setAuthor(author);
        }

        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> getAuthorsAliveInYear(int year) {
        return authorRepository.findAuthorsAliveInYear(year);
    }

    public List<Book> getBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language.toUpperCase());
    }
}