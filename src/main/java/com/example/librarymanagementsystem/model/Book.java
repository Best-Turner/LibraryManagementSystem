package com.example.librarymanagementsystem.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Book {

    private long id;
    private String title;
    private List<Author> author;
    private Date publicationDate;
    private Genre genre;
    private boolean availability;
    private int pageCount;
    private User user;

    public Book() {
    }

    public Book(long id, String title, List<Author> author, Date publicationDate, Genre genre, boolean availability, int pageCount) {
        this.id = id;
        this.title = title;
        this.author = author;
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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
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
        DRAMA, SATIRE, POETRY, HORROR, FANTASY, FAIRY, TALES, SCIENCE, OTHER;

        public static Genre fromString(String line) {
            return Arrays.stream(values())
                    .filter(el -> el.name().equalsIgnoreCase(line))
                    .findAny().orElse(Genre.OTHER);
        }
    }


}
