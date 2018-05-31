package de.sfl.prpanic.server;

import org.springframework.boot.SpringApplication;

import de.sfl.prpanic.server.config.PrpServerConfiguration;


public class PrpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrpServerConfiguration.class, args);
	}
}
