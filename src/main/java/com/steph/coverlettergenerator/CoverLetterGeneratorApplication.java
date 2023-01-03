package com.steph.coverlettergenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.steph")
@EntityScan(basePackages = "com.steph.entity")
@EnableJpaRepositories(basePackages = "com.steph.model.persistence")
public class CoverLetterGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoverLetterGeneratorApplication.class, args);
	}

}
