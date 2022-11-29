package com.oracle.db23c.config;

import com.oracle.db23c.bo.User;
import com.oracle.db23c.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * In-memory user details service
     *
     * @param encoder
     * @return UserDetailsService
     */
    /*@Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        List<UserDetails> usersList = new ArrayList<>();
        usersList.add(new User(
                "user1", encoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        usersList.add(new User(
                "user2", encoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        return new InMemoryUserDetailsManager(usersList);
    }*/
    @Bean
    public UserDetailsService userDetailsService(UserMapper userMapper) {
        return username -> {
            User user = userMapper.findByUsername(username);
            if (user != null) return user;

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .requestMatchers("/main/admin")
                .hasRole("ADMIN")
                .requestMatchers("/main")
                .hasRole("USER")
                .requestMatchers("/", "/**")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .defaultSuccessUrl("/main/index", true)
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .and()
                .build();
    }
}
