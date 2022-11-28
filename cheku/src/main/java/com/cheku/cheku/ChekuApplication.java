package com.cheku.cheku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class ChekuApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ChekuApplication.class, args);
	}
}
