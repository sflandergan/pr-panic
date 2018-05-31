package de.sfl.prpanic.server.config.infrastructure.kafka.streams.applications;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology.AutoOffsetReset;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.sfl.prpanic.server.model.avro.Project;
import de.sfl.prpanic.server.model.avro.ProjectMetrics;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;

@Configuration
public class CodeMetricsKafkaStreamsConfig {

	@Bean
	public void projectMetricsInitializer(StreamsBuilder streamsBuilder, StreamsConfig config,
			SpecificAvroSerde<Project> inputValueSerde, SpecificAvroSerde<ProjectMetrics> outputValueSerde) {
		
		Consumed<String, Project> consumed = Consumed.<String, Project>with(AutoOffsetReset.EARLIEST)
					.withKeySerde(Serdes.String())
					.withValueSerde(inputValueSerde);
		
		streamsBuilder.stream(appendApplicationIdToTopic("projects", config), consumed)
			.mapValues(this::projectToProjectMetrics);
	}
	
	private ProjectMetrics projectToProjectMetrics(Project project) {
		return null;
	}

	private String appendApplicationIdToTopic(String topic, StreamsConfig config) {
		return String.format("%s-%s", config.getString(StreamsConfig.APPLICATION_ID_CONFIG));
	}
}
