package com.example.librarymanagementsystem.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    private String lastname;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> works;

    public Author() {
    }

    public Author(long id, String firstname, String lastname, List<Book> works) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.works = works;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Book> getWorks() {
        return works;
    }

    public void setWorks(List<Book> works) {
        this.works = works;
    }

    @Override
    public String toString() {
        return "Author{" +
               "id=" + id +
               ", firstname='" + firstname + '\'' +
               ", lastname='" + lastname;
    }
}
