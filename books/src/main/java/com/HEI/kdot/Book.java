package com.HEI.kdot;

import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private int nbrPages;
    private LocalDate parutionDate;
    private String resume;
    private Genre genre;
    private Author author;

    public Book(int id, Author author, Genre genre, LocalDate parutionDate, String resume, int nbrPages, String title) {
        setId(id);
        setNbrPages(nbrPages);
        setParutionDate(parutionDate);
        this.author = author;
        this.genre = genre;
        this.resume = resume;
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Zero or negative ID? Are you trying to delete the book from reality?");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Even Voldemort had a name. Give the book a title.");
        }
        this.title = title;
    }

    public int getNbrPages() {
        return nbrPages;
    }

    public void setNbrPages(int nbrPages) {
        if (nbrPages > 0) {
            this.nbrPages = nbrPages;
        } else {
            throw new IllegalArgumentException("Are u high bro ? IS your book out of this dimension");
        }
    }

    public LocalDate getParutionDate() {
        return parutionDate;
    }

    public void setParutionDate(LocalDate parutionDate) {
        if (parutionDate != null && !parutionDate.isAfter(LocalDate.now())) {
            this.parutionDate = parutionDate;
        } else {
            throw new IllegalArgumentException("You can't publish a book tomorrow. Go back to your own timeline, Marty McFly.");
        }
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
