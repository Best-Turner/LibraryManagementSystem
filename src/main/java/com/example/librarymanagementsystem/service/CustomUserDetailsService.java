package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.User;
import com.example.librarymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository repository;

    @Autowired
    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userByFirstname = repository.findUserByEmail(email);
        return userByFirstname.map(u ->
                new org.springframework.security.core.userdetails.User(
                        u.getEmail(),
                        u.getPassword(),
                        Collections.singleton(u.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь с email =  %s не найден", email)));

    }
}
