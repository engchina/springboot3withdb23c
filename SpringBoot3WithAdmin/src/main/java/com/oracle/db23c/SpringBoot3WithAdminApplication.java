package com.oracle.db23c;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SpringBoot3WithAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3WithAdminApplication.class, args);
    }

}
