package com.example.demo.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/api/**")
        .allowedOrigins(
            "http://localhost:5173",
            "https://www.oh-my-family.com",
            "http://192.168.219.104:5173",
            "https://api.oh-my-family.com",
            "http://192.168.219.128/")
        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
        .allowedHeaders("Authorization", "Content-Type", "Accept")
        .allowCredentials(true)
        .maxAge(6000);

    registry
        .addMapping("/**")
        .allowedOrigins(
            "http://localhost:5173",
            "https://www.oh-my-family.com",
            "http://192.168.219.104:5173",
            "https://api.oh-my-family.com",
            "http://192.168.219.128/")
        .allowedMethods("OPTIONS")
        .allowedHeaders("Authorization", "Content-Type", "Accept")
        .allowCredentials(true)
        .maxAge(6000);
  }
}
