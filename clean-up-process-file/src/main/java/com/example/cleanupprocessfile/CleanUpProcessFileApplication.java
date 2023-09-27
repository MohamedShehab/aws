package com.example.cleanupprocessfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CleanUpProcessFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanUpProcessFileApplication.class, args);
	}

}
