package com.victor.sociotorcedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

import lombok.Generated;

@SpringBootApplication
@EnableCircuitBreaker
public class SocioTorcedorApplication {

	@Generated
	public static void main(String[] args) {
		SpringApplication.run(SocioTorcedorApplication.class, args);
	}

}
