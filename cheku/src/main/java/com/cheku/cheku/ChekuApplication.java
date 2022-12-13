package com.cheku.cheku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ChekuApplication implements WebMvcConfigurer {
  
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(ChekuApplication.class, args);
  }
}
