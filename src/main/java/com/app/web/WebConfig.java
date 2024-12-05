package com.app.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Registra un convertidor manual para LocalDate
        registry.addConverter(String.class, LocalDate.class, source -> {
            if (source == null || source.isEmpty()) {
                return null;
            }
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        });
    }
}
