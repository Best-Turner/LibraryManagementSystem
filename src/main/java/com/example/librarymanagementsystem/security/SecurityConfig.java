package com.example.librarymanagementsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/", "/register").permitAll()
                                .requestMatchers(HttpMethod.POST).hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE).hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PATCH).hasAuthority("ADMIN")
                                .anyRequest().authenticated())
                .formLogin(form ->
                        form.loginPage("/login").permitAll()
                                .defaultSuccessUrl("/", true))
                .logout().permitAll();
        return http.build();
    }
}
