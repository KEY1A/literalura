package com.alura.literalura;

import com.alura.literalura.exception.BookAlreadyExistsException;
import com.alura.literalura.exception.BookNotFoundException;
import com.alura.literalura.model.Author;
import com.alura.literalura.model.Book;
import com.alura.literalura.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleApplication implements CommandLineRunner {
    private final BookService bookService;
    private final Scanner scanner;

    public ConsoleApplication(BookService bookService) {
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) {
        while (true) {
            printMenu();
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (option) {
                    case 1:
                        searchBook();
                        break;
                    case 2:
                        listBooks();
                        break;
                    case 3:
                        listAuthors();
                        break;
                    case 4:
                        listAuthorsAliveInYear();
                        break;
                    case 5:
                        listBooksByLanguage();
                        break;
                    case 6:
                        System.out.println("¡Hasta luego!");
                        return;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (BookNotFoundException | BookAlreadyExistsException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Buscar libro por título");
        System.out.println("2. Listar libros");
        System.out.println("3. Listar autores");
        System.out.println("4. Listar autores vivos en un año");
        System.out.println("5. Listar libros por idioma");
        System.out.println("6. Salir");
        System.out.print("Elija una opción: ");
    }

    private void searchBook() {
        System.out.print("Ingrese el título del libro: ");
        String title = scanner.nextLine();
        Book book = bookService.searchAndSaveBook(title);
        System.out.println("Libro guardado: " + book.getTitle());
    }

    private void listBooks() {
        List<Book> books = bookService.getAllBooks();
        books.forEach(book -> System.out.println(
            book.getTitle() + " - " + 
            (book.getAuthor() != null ? book.getAuthor().getName() : "Autor desconocido") + 
            " - " + book.getLanguage()
        ));
    }

    private void listAuthors() {
        List<Author> authors = bookService.getAllAuthors();
        authors.forEach(author -> System.out.println(
            author.getName() + " (" + 
            (author.getBirthYear() != null ? author.getBirthYear() : "?") + " - " + 
            (author.getDeathYear() != null ? author.getDeathYear() : "?") + ")"
        ));
    }

    private void listAuthorsAliveInYear() {
        System.out.print("Ingrese el año: ");
        int year = scanner.nextInt();
        List<Author> authors = bookService.getAuthorsAliveInYear(year);
        authors.forEach(author -> System.out.println(author.getName()));
    }

    private void listBooksByLanguage() {
        System.out.print("Ingrese el código del idioma (ES, EN, FR, PT): ");
        String language = scanner.nextLine();
        List<Book> books = bookService.getBooksByLanguage(language);
        books.forEach(book -> System.out.println(book.getTitle()));
    }
}