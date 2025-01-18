package com.example.librarymanagementsystem.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    private String lastname;
    private String password;
    private Date birthday;
    private Role role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Book> readsBooks;


    public User() {
        this.readsBooks = new ArrayList<>();
    }

    public User(long id, String firstname, String lastname, String password, Date birthday, Role role) {
        super();
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.birthday = birthday;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Book> getReadsBooks() {
        return readsBooks;
    }

    public void setReadsBooks(List<Book> readsBooks) {
        this.readsBooks = readsBooks;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", firstname='" + firstname + '\'' +
               ", lastname='" + lastname + '\'' +
               ", password='" + password + '\'' +
               ", birthday=" + birthday +
               ", role=" + role +
               ", readsBooks=" + readsBooks +
               '}';
    }

    public enum Role {
        USER, ADMIN
    }
}
