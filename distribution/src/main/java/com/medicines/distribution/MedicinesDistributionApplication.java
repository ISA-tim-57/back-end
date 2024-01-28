package com.medicines.distribution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MedicinesDistributionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicinesDistributionApplication.class, args);
	}

}
