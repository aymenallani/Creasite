package com.Projet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CreasiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreasiteApplication.class, args);
	}

}
