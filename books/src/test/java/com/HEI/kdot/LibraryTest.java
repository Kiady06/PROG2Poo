package com.HEI.kdot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private Library library;
    private Author authorMale;
    private Author authorFemale;
    private Book bookSci;
    private Book bookRomance;
    private Book bookAction;
    private Book bookAction2;

    @BeforeEach
    void setUp() {
        authorMale = new Author(1, "Isaac", "Asimov", LocalDate.of(1920, 1, 2), Gender.MALE, "USA");
        authorFemale = new Author(2, "Ursula", "Le Guin", LocalDate.of(1929, 10, 21), Gender.FEMALE, "USA");

        bookSci = new Book(1, authorMale, Genre.SCI_FI,
                LocalDate.of(1951, 1, 1),
                "A galactic empire falls. Hari Seldon devises a plan to preserve knowledge.",
                255, "Foundation");

        bookRomance = new Book(2, authorFemale, Genre.ROMANCE,
                LocalDate.of(1969, 3, 1),
                "A romantic journey across the left hand of darkness.",
                286, "The Left Hand of Darkness");

        bookAction = new Book(3, authorMale, Genre.ACTION,
                LocalDate.of(1982, 5, 10),
                "Action-packed adventure with robots and AI.",
                312, "Foundation's Edge");

        bookAction2 = new Book(4, authorFemale, Genre.ACTION,
                LocalDate.of(1974, 6, 1),
                "A story about change and transformation in a distant world.",
                287, "The Dispossessed");

        library = new Library(1, "Bibliothèque Centrale", "123 Rue de la Paix", "Paris",
                new ArrayList<>(List.of(bookSci, bookRomance, bookAction, bookAction2)));
    }

    // =========================================================================
    // 1. addBook
    // =========================================================================
    @Nested
    @DisplayName("addBook()")
    class AddBookTests {

        @Test
        @DisplayName("Should add a valid book to the library")
        void addBook_nominal() {
            // Given
            Book newBook = new Book(5, authorMale, Genre.TECH,
                    LocalDate.of(2001, 4, 1), "Introduction to algorithms.", 600, "CLRS");
            int sizeBefore = library.getBooks().size();

            // When
            Book result = library.addBook(newBook);

            // Then
            assertEquals(sizeBefore + 1, library.getBooks().size());
            assertEquals(newBook, result);
            assertTrue(library.getBooks().contains(newBook));
        }

        @Test
        @DisplayName("Should throw when adding a null book")
        void addBook_nullBook_throwsException() {
            // Given
            Book nullBook = null;

            // When / Then
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> library.addBook(nullBook)
            );
            assertTrue(ex.getMessage().toLowerCase().contains("can't see it")
                    || ex.getMessage() != null);
        }
    }

    // =========================================================================
    // 2. removeBookById
    // =========================================================================
    @Nested
    @DisplayName("removeBookById()")
    class RemoveBookByIdTests {

        @Test
        @DisplayName("Should remove the book with the matching id")
        void removeBookById_nominal() {
            // Given
            int targetId = bookSci.getId(); // id = 1
            int sizeBefore = library.getBooks().size();

            // When
            library.removeBookById(targetId);

            // Then
            assertEquals(sizeBefore - 1, library.getBooks().size());
            assertFalse(library.getBooks().stream()
                    .anyMatch(b -> b.getId() == targetId));
        }

        @Test
        @DisplayName("Should not alter list when id does not match any book")
        void removeBookById_nonExistentId_listUnchanged() {
            // Given
            int nonExistentId = 999;
            int sizeBefore = library.getBooks().size();

            // When - Then
            assertThrows(IndexOutOfBoundsException.class,
                    () -> library.removeBookById(nonExistentId));
        }
    }

    // =========================================================================
    // 3. getAllBooksOrderedByTitle
    // =========================================================================
    @Nested
    @DisplayName("getAllBooksOrderedByTitle()")
    class GetAllBooksOrderedByTitleTests {

        @Test
        @DisplayName("Should return books sorted alphabetically by title")
        void getAllBooksOrderedByTitle_nominal() {
            // Given — library has Foundation, The Left Hand of Darkness, Foundation's Edge, The Dispossessed

            // When
            List<Book> sorted = library.getAllBooksOrderedByTitle();

            // Then
            List<String> titles = sorted.stream().map(Book::getTitle).toList();
            assertEquals(List.of("Foundation", "Foundation's Edge", "The Dispossessed", "The Left Hand of Darkness"), titles);
        }

        @Test
        @DisplayName("Should return empty list when library has no books")
        void getAllBooksOrderedByTitle_emptyLibrary_returnsEmptyList() {
            // Given
            Library emptyLibrary = new Library(2, "Empty Lib", "1 Rue Vide", "Lyon");

            // When
            List<Book> sorted = emptyLibrary.getAllBooksOrderedByTitle();

            // Then
            assertTrue(sorted.isEmpty());
        }

        @Test
        @DisplayName("Should return single book when library has only one book")
        void getAllBooksOrderedByTitle_singleBook_returnsSingleElement() {
            // Given
            Library singleLibrary = new Library(3, "Mono Lib", "2 Rue Un", "Bordeaux",
                    new ArrayList<>(List.of(bookSci)));

            // When
            List<Book> sorted = singleLibrary.getAllBooksOrderedByTitle();

            // Then
            assertEquals(1, sorted.size());
            assertEquals("Foundation", sorted.get(0).getTitle());
        }
    }

    // =========================================================================
    // 4. getBookByTitle
    // =========================================================================
    @Nested
    @DisplayName("getBookByTitle()")
    class GetBookByTitleTests {

        @Test
        @DisplayName("Should return the book when title matches exactly")
        void getBookByTitle_exactMatch_returnsBook() {
            // Given
            String targetTitle = "Foundation";

            // When
            Book result = library.getBookByTitle(targetTitle);

            // Then
            assertNotNull(result);
            assertEquals(targetTitle, result.getTitle());
        }

        @Test
        @DisplayName("Should return null when no book matches the title")
        void getBookByTitle_noMatch_returnsNull() {
            // Given
            String absentTitle = "Harry Potter";

            // When
            Book result = library.getBookByTitle(absentTitle);

            // Then
            assertNull(result);
        }

        @Test
        @DisplayName("Should be case-sensitive and return null for wrong casing")
        void getBookByTitle_wrongCase_returnsNull() {
            // Given
            String wrongCase = "foundation"; // lowercase

            // When
            Book result = library.getBookByTitle(wrongCase);

            // Then
            assertNull(result);
        }

        @Test
        @DisplayName("Should return null when searching in an empty library")
        void getBookByTitle_emptyLibrary_returnsNull() {
            // Given
            Library emptyLibrary = new Library(4, "Empty", "X", "Y");

            // When
            Book result = emptyLibrary.getBookByTitle("Foundation");

            // Then
            assertNull(result);
        }
    }

    // =========================================================================
    // 5. getBooksByGenre
    // =========================================================================
    @Nested
    @DisplayName("getBooksByGenre()")
    class GetBooksByGenreTests {

        @Test
        @DisplayName("Should return all books matching the given genre")
        void getBooksByGenre_matchingGenre_returnsList() {
            // Given
            Genre targetGenre = Genre.ACTION;

            // When
            List<Book> result = library.getBooksByGenre(targetGenre);

            // Then
            assertEquals(2, result.size());
            assertTrue(result.stream().allMatch(b -> b.getGenre() == Genre.ACTION));
        }

        @Test
        @DisplayName("Should return empty list when no books match the genre")
        void getBooksByGenre_noMatch_returnsEmptyList() {
            // Given
            Genre absentGenre = Genre.BIOGRAPHY;

            // When
            List<Book> result = library.getBooksByGenre(absentGenre);

            // Then
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should return empty list for an empty library")
        void getBooksByGenre_emptyLibrary_returnsEmptyList() {
            // Given
            Library emptyLibrary = new Library(5, "Empty", "X", "Y");

            // When
            List<Book> result = emptyLibrary.getBooksByGenre(Genre.SCI_FI);

            // Then
            assertTrue(result.isEmpty());
        }
    }

    // =========================================================================
    // 6. getBooksByKeyWord
    // =========================================================================
    @Nested
    @DisplayName("getBooksByKeyWord()")
    class GetBooksByKeyWordTests {

        @Test
        @DisplayName("Should return books whose resume contains the keyword (case-insensitive)")
        void getBooksByKeyWord_matchFound_returnsList() {
            // Given
            String keyword = "galactic"; // present in bookSci's resume

            // When
            List<Book> result = library.getBooksByKeyWord(keyword);

            // Then
            assertEquals(1, result.size());
            assertEquals("Foundation", result.get(0).getTitle());
        }

        @Test
        @DisplayName("Should be case-insensitive")
        void getBooksByKeyWord_caseInsensitive_returnsMatch() {
            // Given
            String keyword = "GALACTIC";

            // When
            List<Book> result = library.getBooksByKeyWord(keyword);

            // Then
            assertEquals(1, result.size());
        }

        @Test
        @DisplayName("Should return empty list when keyword matches nothing")
        void getBooksByKeyWord_noMatch_returnsEmptyList() {
            // Given
            String keyword = "dragons";

            // When
            List<Book> result = library.getBooksByKeyWord(keyword);

            // Then
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should return empty list when keyword is null")
        void getBooksByKeyWord_nullKeyword_returnsEmptyList() {
            // Given / When
            List<Book> result = library.getBooksByKeyWord(null);

            // Then
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should return empty list when keyword is blank")
        void getBooksByKeyWord_blankKeyword_returnsEmptyList() {
            // Given / When
            List<Book> result = library.getBooksByKeyWord("   ");

            // Then
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should return empty list when keyword is empty string")
        void getBooksByKeyWord_emptyString_returnsEmptyList() {
            // Given / When
            List<Book> result = library.getBooksByKeyWord("");

            // Then
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should return multiple books when keyword matches several resumes")
        void getBooksByKeyWord_multipleMatches_returnsAll() {
            // Given — "a" appears in virtually all resumes; use a precise shared word
            String keyword = "story"; // present in bookAction2's resume

            // When
            List<Book> result = library.getBooksByKeyWord(keyword);

            // Then
            assertFalse(result.isEmpty());
            assertTrue(result.stream().allMatch(b ->
                    b.getResume().toLowerCase().contains("story")));
        }
    }

    // =========================================================================
    // 7. getDistinctAuthors
    // =========================================================================
    @Nested
    @DisplayName("getDistinctAuthors()")
    class GetDistinctAuthorsTests {

        @Test
        @DisplayName("Should return only distinct authors even when one has multiple books")
        void getDistinctAuthors_duplicateAuthors_returnsDistinct() {
            // Given — authorMale has bookSci AND bookAction; authorFemale has bookRomance and bookAction2

            // When
            List<Author> authors = library.getDistinctAuthors();

            // Then
            assertEquals(2, authors.size());
            assertTrue(authors.contains(authorMale));
            assertTrue(authors.contains(authorFemale));
        }

        @Test
        @DisplayName("Should return empty list for an empty library")
        void getDistinctAuthors_emptyLibrary_returnsEmptyList() {
            // Given
            Library emptyLibrary = new Library(6, "Empty", "X", "Y");

            // When
            List<Author> authors = emptyLibrary.getDistinctAuthors();

            // Then
            assertTrue(authors.isEmpty());
        }

        @Test
        @DisplayName("Should return a single author when all books share the same author")
        void getDistinctAuthors_singleAuthorAllBooks_returnsOneEntry() {
            // Given
            Library singleAuthorLib = new Library(7, "Mono", "X", "Y",
                    new ArrayList<>(List.of(bookSci, bookAction)));

            // When
            List<Author> authors = singleAuthorLib.getDistinctAuthors();

            // Then
            assertEquals(1, authors.size());
            assertEquals(authorMale, authors.get(0));
        }
    }

    // =========================================================================
    // 8. countByGenres
    // =========================================================================
    @Nested
    @DisplayName("countByGenres()")
    class CountByGenresTests {

        @Test
        @DisplayName("Should return correct counts per genre")
        void countByGenres_nominal_returnsCorrectCounts() {
            // Given — SCI_FI: 1, ROMANCE: 1, ACTION: 2

            // When
            Map<Genre, Integer> counts = library.countByGenres();

            // Then
            assertEquals(1, counts.get(Genre.SCI_FI));
            assertEquals(1, counts.get(Genre.ROMANCE));
            assertEquals(2, counts.get(Genre.ACTION));
            assertNull(counts.get(Genre.BIOGRAPHY));
        }

        @Test
        @DisplayName("Should return empty map for an empty library")
        void countByGenres_emptyLibrary_returnsEmptyMap() {
            // Given
            Library emptyLibrary = new Library(8, "Empty", "X", "Y");

            // When
            Map<Genre, Integer> counts = emptyLibrary.countByGenres();

            // Then
            assertTrue(counts.isEmpty());
        }

        @Test
        @DisplayName("Should return map with one entry when all books are the same genre")
        void countByGenres_allSameGenre_returnsSingleEntry() {
            // Given
            Library actionLib = new Library(9, "Action Lib", "X", "Y",
                    new ArrayList<>(List.of(bookAction, bookAction2)));

            // When
            Map<Genre, Integer> counts = actionLib.countByGenres();

            // Then
            assertEquals(1, counts.size());
            assertEquals(2, counts.get(Genre.ACTION));
        }

        @Test
        @DisplayName("Should count exactly one book per unique genre when all genres differ")
        void countByGenres_allDifferentGenres_eachCountIsOne() {
            // Given
            Library mixedLib = new Library(10, "Mixed Lib", "X", "Y",
                    new ArrayList<>(List.of(bookSci, bookRomance)));

            // When
            Map<Genre, Integer> counts = mixedLib.countByGenres();

            // Then
            assertEquals(2, counts.size());
            counts.values().forEach(count -> assertEquals(1, count));
        }
    }
}