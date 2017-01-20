package com.leo;

import com.leo.util.Loggable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application extends Loggable {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}