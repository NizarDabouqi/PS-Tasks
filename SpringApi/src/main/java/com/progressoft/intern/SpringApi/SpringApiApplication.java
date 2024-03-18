package com.progressoft.intern.SpringApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
public class SpringApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringApiApplication.class, args);
	}

}
