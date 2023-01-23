package com.oracle.db23c.springboot3withnativeimage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBoot3WithNativeImageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3WithNativeImageApplication.class, args);
    }

    @RequestMapping("/")
    String sayHello() {
        return "Hello World!";
    }
}
