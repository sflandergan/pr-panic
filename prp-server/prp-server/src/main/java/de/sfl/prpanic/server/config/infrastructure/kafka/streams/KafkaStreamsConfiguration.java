package de.sfl.prpanic.server.config.infrastructure.kafka.streams;

import org.apache.kafka.streams.StreamsConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;

@EnableKafka
@EnableKafkaStreams
@Configuration
@EnableConfigurationProperties(KafkaStreamsProperties.class)
public class KafkaStreamsConfiguration {
	
	@Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
	public StreamsConfig kafkaStreamsConfig(KafkaStreamsProperties properties) {
		return properties.buildConfig();
	}
	
	
	
}
