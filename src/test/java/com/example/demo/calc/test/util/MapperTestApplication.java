package com.example.demo.calc.test.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("calc.domain.model")
@SpringBootApplication(scanBasePackages="calc.domain.repository")
public class MapperTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapperTestApplication.class, args);
	}
}
