package de.sfl.prpanic.server.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import de.sfl.prpanic.server.config.infrastructure.kafka.streams.KafkaStreamsConfiguration;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import(KafkaStreamsConfiguration.class)
public class PrpServerConfiguration {
	
}
