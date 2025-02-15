package com.example.librarymanagementsystem.web;

import com.example.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AuthorRepository repository;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
    }

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new AuthorByIdConverter(repository));
//    }

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
