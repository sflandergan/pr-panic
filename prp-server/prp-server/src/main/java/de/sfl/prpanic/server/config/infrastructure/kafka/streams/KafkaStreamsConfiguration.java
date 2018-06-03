package de.sfl.prpanic.server.config.infrastructure.kafka.streams;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;

import de.sfl.prpanic.server.infrastructure.kafka.streams.CodeMetricsAggregator;
import de.sfl.prpanic.server.model.avro.ClassIdentifier;
import de.sfl.prpanic.server.model.avro.Project;
import de.sfl.prpanic.server.model.avro.ProjectMetrics;
import de.sfl.prpanic.server.model.avro.TransactionsSnapshot;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;

@EnableKafka
@EnableKafkaStreams
@Configuration
@EnableConfigurationProperties(KafkaStreamsProperties.class)
@Import(KafkaSerdeConfiguration.class)
public class KafkaStreamsConfiguration {

	@Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
	public StreamsConfig kafkaStreamsConfig(KafkaStreamsProperties properties) {
		return properties.buildConfig();
	}

	@Bean
	public CodeMetricsAggregator codeMetricsAggregator(StreamsBuilder builder, StreamsConfig streamsConfig,
			SpecificAvroSerde<ClassIdentifier> classIdentifierKs, SpecificAvroSerde<TransactionsSnapshot> txSnapshot,
			SpecificAvroSerde<ProjectMetrics> projectMetricsVs, SpecificAvroSerde<Project> projectKs) {

		return new CodeMetricsAggregator(builder, streamsConfig, classIdentifierKs, txSnapshot, projectMetricsVs,
				projectKs);
	}

}
