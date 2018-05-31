package de.sfl.prpanic.server;

import org.springframework.boot.SpringApplication;

import de.sfl.prpanic.server.config.PrpServerConfig;


public class PrpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrpServerConfig.class, args);
	}
}
