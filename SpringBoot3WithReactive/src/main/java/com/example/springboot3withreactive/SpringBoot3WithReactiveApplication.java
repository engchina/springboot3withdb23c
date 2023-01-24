package com.example.springboot3withreactive;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@ImportRuntimeHints(SpringBoot3WithReactiveApplication.ResourceRuntimeHints.class)
public class SpringBoot3WithReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3WithReactiveApplication.class, args);
    }

    public static class ResourceRuntimeHints implements RuntimeHintsRegistrar {
        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            hints.resources().registerPattern("org/springframework/web/context/ContextLoader.properties");
        }
    }
}
