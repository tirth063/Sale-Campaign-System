package com.sb.salecampion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SalecampionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalecampionApplication.class, args);
	}

}
