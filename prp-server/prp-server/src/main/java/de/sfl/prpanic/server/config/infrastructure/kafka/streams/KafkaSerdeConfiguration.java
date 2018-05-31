package de.sfl.prpanic.server.config.infrastructure.kafka.streams;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.sfl.prpanic.server.model.avro.Project;
import de.sfl.prpanic.server.model.avro.ProjectMetrics;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;

@Configuration
@EnableConfigurationProperties(SchemaRegistryProperties.class)
public class KafkaSerdeConfiguration {
	
	@Bean
	public SpecificAvroSerde<Project> projectValueSerde(SchemaRegistryProperties properties) {
		SpecificAvroSerde<Project> serde = new SpecificAvroSerde<>();
		serde.configure(properties.buildConfig(), false);
		return serde;
	}
	
	@Bean
	public SpecificAvroSerde<ProjectMetrics> projectMetricsValueSerde(SchemaRegistryProperties properties) {
		SpecificAvroSerde<ProjectMetrics> serde = new SpecificAvroSerde<>();
		serde.configure(properties.buildConfig(), false);
		return serde;
	}

}
