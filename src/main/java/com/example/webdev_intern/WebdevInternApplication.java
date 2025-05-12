package com.example.webdev_intern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WebdevInternApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebdevInternApplication.class, args);
	}

}
