package com.example.springboot3withreactive.config;

import com.example.springboot3withreactive.dto.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class StartUp {

    public static Map<String, Employee> DATABASE = new LinkedHashMap<>();

    @Bean
    CommandLineRunner initDatabase(){
        return args -> {
            DATABASE.put("Frodo Baggins", new Employee("Frodo Baggins", "ring bearer"));
            DATABASE.put("Samwise Gamgee", new Employee("Samwise Gamgee", "gardener"));
            DATABASE.put("Bilbo Baggis", new Employee("Bilbo Baggins", "burglar"));
        };
    }
}
