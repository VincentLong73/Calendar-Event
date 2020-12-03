package com.hedspi.team45;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CalendarEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendarEventApplication.class, args);
	}

}
