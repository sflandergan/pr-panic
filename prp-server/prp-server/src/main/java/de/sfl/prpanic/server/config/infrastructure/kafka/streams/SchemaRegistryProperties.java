package de.sfl.prpanic.server.config.infrastructure.kafka.streams;

import static io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig.MAX_SCHEMAS_PER_SUBJECT_CONFIG;
import static io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka.schema.registry")
public class SchemaRegistryProperties {

	private final Map<String, Object> config = new HashMap<>();
	
	public Map<String, Object> buildConfig() {
		return new HashMap<>(config);
	}
	
	// schema.registry.url
	public void setUrl(String url) {
		config.put(SCHEMA_REGISTRY_URL_CONFIG, url);
	}

	// max.schemas.per.subject
	public void setMaxSchemasPerSuject(int maxSchemas) {
		config.put(MAX_SCHEMAS_PER_SUBJECT_CONFIG, maxSchemas);
	}
}
