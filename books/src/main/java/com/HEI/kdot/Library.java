package com.HEI.kdot;

import java.util.*;

/*
    predicate : fonction qui retourne un boolean
*/

public class Library {
    private int id;
    private String name;
    private String address;
    private String city;
    List<Book> books;

    public Library(int id, String name, String address, String city, List<Book> books) {
        setId(id);
        this.name = name;
        this.address = address;
        this.city = city;
        setBooks(books);
    }

    public Library(int id, String name, String address, String city) {
        setId(id);
        this.name = name;
        this.address = address;
        this.city = city;
        this.books = new ArrayList<>();
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("A library with a negative ID? What is this, a black hole where books unwrite themselves?");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        if (books == null) {
            throw new IllegalArgumentException("You just handed me a null list of books. Even Alexandria after the fire had more books than this.");
        }
        this.books = books;
    }

    public Book addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Are you trying to add a book written by John Cena? Because I can't see it.");
        }
        books.add(book);
        return book;
    }

    public Book removeBookById(int id) {
        books.removeIf(book -> book.getId() == id);
        return books.get(id);
    }

    public List<Book> getAllBooksOrderedByTitle() {
        return books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .toList();
    }

    public Book getBookByTitle(String title) {
        return books.stream().filter(book -> book.getTitle()
                .equals(title))
                .findFirst()
                .orElse(null);
    }

    public List<Book> getBooksByGenre(Genre genre) {
        return books.stream().filter(book -> book.getGenre()
                .equals(genre))
                .toList();
    }

    public List<Book> getBooksByKeyWord(String keyWord) {
        if (keyWord == null || keyWord.isBlank()) {
            return Collections.emptyList();
        }

        return books.stream()
                .filter(book -> book.getResume()
                        .toLowerCase()
                        .contains(keyWord.toLowerCase()))
                .toList();
    }

    public List<Author> getDistinctAuthors() {
        return books.stream()
                .map(Book::getAuthor)
                .distinct()
                .toList();
    }

    public Map<Genre, Integer> countByGenres() {
        Map<Genre, Integer> countByGenre = new HashMap<>();

        for (Book book : books) {
            Genre genre = book.getGenre();
            if (countByGenre.containsKey(genre)) {
                countByGenre.put(genre, countByGenre.get(genre) + 1);
            } else {
                countByGenre.put(genre, 1);
            }
        }

        return countByGenre;
    }
}
