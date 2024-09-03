package com.minibank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure HTTP security
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/h2-console/**").permitAll() // Allow access to H2 console
                                .requestMatchers("/auth/login").permitAll() // Allow access to login page
                                .requestMatchers("/public/**").permitAll() // Allow access to public pages
                                .anyRequest().authenticated() // All other endpoints require authentication
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/auth/login") // Custom login page
                                .defaultSuccessUrl("/home", true) // Redirect to home page on successful login
                                .failureUrl("/auth/login?error") // Redirect to login page with error on failure
                )
                .logout(logout ->
                        logout
                                .logoutSuccessUrl("/auth/login?logout") // Redirect to login page on logout
                )
                .headers(headers ->
                        headers
                                .addHeaderWriter((request, response) -> {
                                    // Allow frames to access the H2 console
                                    response.setHeader("X-Frame-Options", "ALLOW-FROM http://localhost:8080");
                                })
                );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/public/**");
    }
}
