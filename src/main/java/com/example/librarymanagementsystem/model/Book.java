package com.example.librarymanagementsystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "books", schema = "model")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            schema = "model",
            name = "author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> author;

    private LocalDate publicationDate;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private boolean availability;

    private int pageCount;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Book() {
        author = new ArrayList<>();
    }

    public Book(long id, String title, LocalDate publicationDate, Genre genre, boolean availability, int pageCount) {
        super();
        this.id = id;
        this.title = title;
        this.publicationDate = publicationDate;
        this.genre = genre;
        this.availability = availability;
        this.pageCount = pageCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Book{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", author=" + author +
               ", publicationDate=" + publicationDate +
               ", genre=" + genre +
               ", availability=" + availability +
               ", pageCount=" + pageCount +
               ", user=" + user +
               '}';
    }

    public enum Genre {
        DRAMA, SATIRE, POETRY, HORROR, FANTASY, FAIRY, TALES, SCIENCE, ROMAN, OTHER;

        public static Genre fromString(String line) {
            return Arrays.stream(values())
                    .filter(el -> el.name().equalsIgnoreCase(line))
                    .findAny().orElse(Genre.OTHER);
        }
    }


}
