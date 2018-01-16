package com.scl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class MicroserviceHystrixTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceHystrixTurbineApplication.class, args);
	}
}
