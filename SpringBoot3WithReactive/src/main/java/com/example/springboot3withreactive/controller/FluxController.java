package com.example.springboot3withreactive.controller;

import com.example.springboot3withreactive.dto.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.springboot3withreactive.config.StartUp.DATABASE;

@Controller
public class FluxController {

    @GetMapping("/")
    public Mono<Rendering> index() {
        return Flux.fromIterable(DATABASE.values())
                .collectList()
                .map(employees -> Rendering.view("index")
                        .modelAttribute("employees", employees)
                        .modelAttribute("newEmployee", new Employee("", ""))
                        .build());

    }

    @PostMapping("/new-employee")
    Mono<String> newEmployee(@ModelAttribute Mono<Employee> newEmployee) {
        return newEmployee //
                .map(employee -> {
                    DATABASE.put(employee.name(), employee);
                    return "redirect:/";
                });
    }
}
