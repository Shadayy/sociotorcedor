package com.victor.sociotorcedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class SocioTorcedorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocioTorcedorApplication.class, args);
	}

}
