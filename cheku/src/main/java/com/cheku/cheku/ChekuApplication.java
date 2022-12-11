package com.cheku.cheku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@SpringBootApplication
public class ChekuApplication implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("*");
  }
  
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(ChekuApplication.class, args);
  }
}
