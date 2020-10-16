package com.eyeson.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.eyeson.service")
//@ComponentScan("com.eyeson.service")
public class TempMeetingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TempMeetingApplication.class, args);
	}

}
