package com.cheku.cheku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@SpringBootApplication
public class ChekuApplication implements WebMvcConfigurer {
  public static void main(String[] args) {
    SpringApplication.run(ChekuApplication.class, args);
  }
}
