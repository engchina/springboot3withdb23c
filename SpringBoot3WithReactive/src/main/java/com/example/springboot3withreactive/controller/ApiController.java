package com.example.springboot3withreactive.controller;

import com.example.springboot3withreactive.dto.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.springboot3withreactive.config.StartUp.DATABASE;

@RestController
public class ApiController {

    @GetMapping("/api/employees")
    Flux<Employee> employees() {
        return Flux.just(
                new Employee("alice", "management"),
                new Employee("bob", "payroll")
        );
    }

    @PostMapping("/api/employees")
    Mono<Employee> add(@RequestBody Mono<Employee> newEmployee) {
        return newEmployee //
                .map(employee -> {
                    DATABASE.put(employee.name(), employee);
                    return employee;
                });
    }
}
