package com.aptechph.task_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PollSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollSystemApplication.class, args);
	}

}

