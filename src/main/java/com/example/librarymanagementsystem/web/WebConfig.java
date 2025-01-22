package com.example.librarymanagementsystem.web;

import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Optional;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(BookRepository bookRepository,
//                                               AuthorRepository authorRepository) throws ParseException {
//
//        return args -> {
//            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//
//            Author levTolstoy = new Author();
//            levTolstoy.setFirstname("Лев");
//            levTolstoy.setLastname("Толстой");
//
//            Author dostoevsky = new Author();
//            dostoevsky.setFirstname("Федор");
//            dostoevsky.setLastname("Достоевский");
//
//
//            Book warAndPeace = new Book();
//            //warAndPeace.setAuthor(Collections.singletonList(levTolstoy));
//            warAndPeace.setTitle("Война и мир");
//            warAndPeace.setAvailability(true);
//            warAndPeace.setGenre(Book.Genre.DRAMA);
//            warAndPeace.setPublicationDate(format.parse("1869/01/01"));
//            warAndPeace.setPageCount(650);
//
//            Book prestupleniyeNakazaniye = new Book();
//            prestupleniyeNakazaniye.setAuthor(Collections.singletonList(dostoevsky));
//            prestupleniyeNakazaniye.setTitle("Преступление и наказание");
//            prestupleniyeNakazaniye.setAvailability(true);
//            prestupleniyeNakazaniye.setGenre(Book.Genre.ROMAN);
//            prestupleniyeNakazaniye.setPublicationDate(format.parse("1866/01/01"));
//            prestupleniyeNakazaniye.setPageCount(485);
//            levTolstoy.setWorks(Collections.singletonList(warAndPeace));
//            dostoevsky.setWorks(Collections.singletonList(prestupleniyeNakazaniye));
//            authorRepository.save(levTolstoy);
//            authorRepository.save(dostoevsky);
//            bookRepository.save(warAndPeace);
//            bookRepository.save(prestupleniyeNakazaniye);
//
//
//            System.out.println(warAndPeace.getAuthor());
//            Optional<Book> byId = bookRepository.findById(1L);
//
//        };
//    }
}
