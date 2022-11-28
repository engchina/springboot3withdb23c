package com.oracle.db23c.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The main thing it does is declare a PasswordEncoder bean, which we’ll use both when creating new users and when authenticating users at login.
 * we’re using BCryptPasswordEncoder, one of a handful of password encoders provided by Spring Security, including the following:
 * <p>
 * 1. BCryptPasswordEncoder—Applies bcrypt strong hashing encryption
 * 2. NoOpPasswordEncoder—Applies no encoding
 * 3. Pbkdf2PasswordEncoder—Applies PBKDF2 encryption
 * 4. SCryptPasswordEncoder—Applies Scrypt hashing encryption
 * 5. StandardPasswordEncoder—Applies SHA-256 hashing encryption
 */
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        List<UserDetails> usersList = new ArrayList<>();
        usersList.add(new User(
                "user1", encoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        usersList.add(new User(
                "user2", encoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        return new InMemoryUserDetailsManager(usersList);
    }
}
