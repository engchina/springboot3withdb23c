package com.oracle.db23c.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * View controllers can be registered with addViewController in a WebMvcConfigurer class to handle HTTP GET requests for which no model data or processing is required.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/main");
        registry.addViewController("/login");
    }
}
