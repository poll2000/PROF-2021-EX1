package es.upm.grise.profundizacion.control_1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryTest {
    
    private Library library;
    
    @BeforeEach
    public void setUp() {
        library = new Library();
    }
    
    @Test
    public void testAddSingleBook() throws DuplicatedBookException, NonExistingBookException, EmptyLibraryException {
        Book book1 = new Book("Libro 1");
        library.addBook(book1);
        // Verificar que el libro se ha agregado correctamente comprobando que se puede recuperar
        Book retrievedBook = library.getBook("Libro 1");
        assertEquals(book1, retrievedBook);
    }
    
    @Test
    public void testAddMultipleBooks() throws DuplicatedBookException, NonExistingBookException, EmptyLibraryException {
        Book book1 = new Book("Libro 1");
        Book book2 = new Book("Libro 2");
        
        library.addBook(book1);
        library.addBook(book2);
        
        // Verificar que los libros se han agregado correctamente comprobando que se pueden recuperar
        Book retrievedBook1 = library.getBook("Libro 1");
        Book retrievedBook2 = library.getBook("Libro 2");
        
        assertEquals(book1, retrievedBook1);
        assertEquals(book2, retrievedBook2);
    }
    
    @Test
    public void testAddDuplicateBook() {
        Book book1 = new Book("Libro 1");
        
        assertDoesNotThrow(() -> library.addBook(book1)); // Añadir el libro por primera vez
        assertThrows(DuplicatedBookException.class, () -> library.addBook(book1)); // Intentar añadirlo de nuevo
    }

    @Test
    public void testRemoveBook() throws DuplicatedBookException, NonExistingBookException {
        Book book1 = new Book("Libro 1");
        library.addBook(book1);
        library.removeBook(book1);
        // Verificar que el libro se ha eliminado correctamente comprobando que no existe en la biblioteca
        assertThrows(NonExistingBookException.class, () -> library.getBook("Libro 1"));
    }

    @Test
    public void testRemoveBookFromEmptyLibrary() {
        // Intentar eliminar un libro de una biblioteca vacía debe lanzar una excepción
        assertThrows(EmptyLibraryException.class, () -> library.removeBook(new Book("Libro 1")));
    }

    @Test
    public void testRemoveNonExistingBook() throws DuplicatedBookException {
        Book book1 = new Book("Libro 1");
        library.addBook(book1);
        // Intentar eliminar un libro que no existe en la biblioteca debe lanzar una excepción
        assertThrows(NonExistingBookException.class, () -> library.removeBook(new Book("Libro 2")));
    }

    @Test
    public void testGetBookFromEmptyLibrary() {
        // Intentar obtener un libro de una biblioteca vacía debe lanzar una excepción
        assertThrows(EmptyLibraryException.class, () -> library.getBook("Libro 1"));
    }

    @Test
    public void testGetNonExistingBook() throws DuplicatedBookException {
        Book book1 = new Book("Libro 1");
        library.addBook(book1);
        // Intentar obtener un libro que no existe en la biblioteca debe lanzar una excepción
        assertThrows(NonExistingBookException.class, () -> library.getBook("Libro 2"));
    }
}

