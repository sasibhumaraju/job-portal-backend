package com.sasibhumaraju.job_portal;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class JobPortalApplication {

	public static void main(String[] args) {
//		Dotenv dotenv = Dotenv.load();  // Loads .env from project root
//		dotenv.entries().forEach(entry ->
//				System.setProperty(entry.getKey(), entry.getValue())
//		);
		SpringApplication.run(JobPortalApplication.class, args);
	}

}
