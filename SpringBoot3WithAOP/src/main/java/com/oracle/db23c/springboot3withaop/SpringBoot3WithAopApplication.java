package com.oracle.db23c.springboot3withaop;

import com.oracle.db23c.springboot3withaop.aspect.AllAspect;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@ImportRuntimeHints(SpringBoot3WithAopApplication.AspectRuntimeHints.class)
public class SpringBoot3WithAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3WithAopApplication.class, args);
    }

    static class AspectRuntimeHints implements RuntimeHintsRegistrar{
        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            hints.reflection().registerType(AllAspect.class, MemberCategory.INVOKE_DECLARED_METHODS);
        }
    }
}
