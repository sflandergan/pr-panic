package de.sfl.prpanic.server.config.infrastructure.kafka.streams;

import static org.apache.kafka.streams.StreamsConfig.APPLICATION_ID_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.APPLICATION_SERVER_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.BUFFERED_RECORDS_PER_PARTITION_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.CLIENT_ID_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.COMMIT_INTERVAL_MS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.*;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.METADATA_MAX_AGE_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.METRICS_NUM_SAMPLES_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.METRIC_REPORTER_CLASSES_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.NUM_STANDBY_REPLICAS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.NUM_STREAM_THREADS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.PROCESSING_GUARANTEE_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.REPLICATION_FACTOR_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.SECURITY_PROTOCOL_CONFIG;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.metrics.MetricsReporter;
import org.apache.kafka.common.metrics.Sensor.RecordingLevel;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.DeserializationExceptionHandler;
import org.apache.kafka.streams.processor.PartitionGrouper;
import org.apache.kafka.streams.processor.TimestampExtractor;
import org.apache.kafka.streams.state.RocksDBConfigSetter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka.streams")
public class KafkaStreamsProperties {

	private final Map<String, Object> properties = new HashMap<>();

	public StreamsConfig buildConfig() {
		return new StreamsConfig(properties);
	}
	
	// application.id
	public void setApplicationId(String applicationId) {
		properties.put(APPLICATION_ID_CONFIG, applicationId);
	}

	// bootstrap.servers
	public void setBootstrapServers(String bootstrapServers) {
		properties.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	}
	
	// replication.factor
	public void setReplicationFactor(int replicationFactor) {
		properties.put(REPLICATION_FACTOR_CONFIG, replicationFactor);
	}
	
	// state.dir
	public void setStateDir(String stateDir) {
		properties.put(STATE_DIR_CONFIG, stateDir);
	}
	
	// commit.interval.ms
	public void setCacheMaxBytesBuffering(long cacheMaxBytesBuffering) {
		properties.put(CACHE_MAX_BYTES_BUFFERING_CONFIG, cacheMaxBytesBuffering);
	}
	
	// client.id
	public void setClientId(String clientId) {
		properties.put(CLIENT_ID_CONFIG, clientId);
	}
	
	// default.deserialization.exception.handler
	public void setDefaultSerializationExceptionHandler(
			Class<? extends DeserializationExceptionHandler> exceptionHandler) {
		properties.put(DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, exceptionHandler);
	}
	
	// default.key.serde
	public void setDefaultKeySerde(Class<? extends Serde<?>> serde) {
		properties.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, serde);
	}
	
	// default.value.serde
	public void setDefaultValueSerde(Class<? extends Serde<?>> serde) {
		properties.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, serde);
	}
	
	// num.standby.replicas
	public void setNumStandbyReplicas(int numStandbyReplicas) {
		properties.put(NUM_STANDBY_REPLICAS_CONFIG, numStandbyReplicas);
	}
	
	// num.stream.threads
	public void setNumStreamThreads(int numStreamThreads) {
		properties.put(NUM_STREAM_THREADS_CONFIG, numStreamThreads);
	}
	
	// processing.guarantee
	public void setProcessingGurantee(String processingGuarantee) {
		properties.put(PROCESSING_GUARANTEE_CONFIG, processingGuarantee);
	}
	
	// security.protocol
	public void setSecurityProtocol(String securityProtocol) {
		properties.put(SECURITY_PROTOCOL_CONFIG, securityProtocol);
	}
	
	// application.server
	public void setApplicationServer(String applicationSercer) {
		properties.put(APPLICATION_SERVER_CONFIG, applicationSercer);
	}

	// buffered.records.per.partition
	public void setBufferedRecordsPerPartition(long bufferedRecordsPerPartition) {
		properties.put(BUFFERED_RECORDS_PER_PARTITION_CONFIG, bufferedRecordsPerPartition);
	}

	// commit.interval.ms
	public void setCommitIntervalMs(long commitIntervalMs) {
		properties.put(COMMIT_INTERVAL_MS_CONFIG, commitIntervalMs);
	}

	// connections.max.idle.ms
	public void setConnectionsMaxIdleMs(long connectionsMaxIdleMs) {
		properties.put(CONNECTIONS_MAX_IDLE_MS_CONFIG, connectionsMaxIdleMs);
	}
	
	// default.timestamp.extractor
	public void setDefaultTimestampExtractor(Class<? extends TimestampExtractor> timestampExtractor) {
		properties.put(DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, timestampExtractor);
	}
	
	// metadata.max.age.ms
	public void setMetadataMaxAgeMs(long maxAgeMs) {
		properties.put(METADATA_MAX_AGE_CONFIG, maxAgeMs);
	}
	
	// metrics.num.samples
	public void setMetricsNumSamples(long numSamples) {
		properties.put(METRICS_NUM_SAMPLES_CONFIG, numSamples);
	}
	
	// metric.reporters
	public void setMetricReporters(List<Class<? extends MetricsReporter>> metricsReporters) {
		properties.put(METRIC_REPORTER_CLASSES_CONFIG, metricsReporters);
	}
	
	// metrics.record.level
	public void setMetricsRecordLevel(RecordingLevel recordingLevel) {
		properties.put(METRICS_RECORDING_LEVEL_CONFIG, recordingLevel);
	}
	
	// metrics.sample.window.ms
	public void setMetricsSampleWindowMs(long sampleWindowMs) {
		properties.put(METRICS_SAMPLE_WINDOW_MS_CONFIG, sampleWindowMs);
	}
	
	// partition.grouper
	public void setPartitionGrouper(Class<? extends PartitionGrouper> partitionGrouper) {
		properties.put(PARTITION_GROUPER_CLASS_CONFIG, partitionGrouper);
	}
	
	// poll.ms
	public void setPollMs(long pollMs) {
		properties.put(POLL_MS_CONFIG, pollMs);
	}

	// receive.buffer.bytes
	public void setReceiveBufferByte(int bufferBytes) {
		properties.put(RECEIVE_BUFFER_CONFIG, bufferBytes);
	}
	
	// reconnect.backoff.ms
	public void setReconnectBackoffMs(long backoffMs) {
		properties.put(RECONNECT_BACKOFF_MS_CONFIG, backoffMs);
	}
	
	// reconnect.backoff.max
	public void setReconnectBackoffMax(long backoffMax) {
		properties.put(RECONNECT_BACKOFF_MAX_MS_CONFIG, backoffMax);
	}
	
	// retries
	public void setRetries(int retries) {
		properties.put(RETRIES_CONFIG, retries);
	}
	
	// retry.backoff.ms
	public void setRetriesBackoffMs(long retriesBackoff) {
		properties.put(RETRY_BACKOFF_MS_CONFIG, retriesBackoff);
	}
	
	// request.timeout.ms
	public void setRequestTimeoutMs(long requestTimeout) {
		properties.put(REQUEST_TIMEOUT_MS_CONFIG, requestTimeout);
	}
	
	// rocksdb.config.setter
	public void setRocksDbConfigSetter(Class<? extends RocksDBConfigSetter> configSetter) {
		properties.put(ROCKSDB_CONFIG_SETTER_CLASS_CONFIG, configSetter);
	}
	
	// send.buffer.bytes
	public void setSentdBufferBytes(int bufferBytes) {
		properties.put(SEND_BUFFER_CONFIG, bufferBytes);
	}
	
	// state.cleanup.delay
	public void setStateCleanupDelay(long cleanUpDelay) {
		properties.put(STATE_CLEANUP_DELAY_MS_CONFIG, cleanUpDelay);
	}
	
	// windowstore.changelog.additional.retention.ms
	public void setWindowstoreChangelogAdditionalRetentionMs(long retention) {
		properties.put(WINDOW_STORE_CHANGE_LOG_ADDITIONAL_RETENTION_MS_CONFIG, retention);
	}
	
}
