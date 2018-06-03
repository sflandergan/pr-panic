package de.sfl.prpanic.server.infrastructure.kafka.streams;

import java.math.BigInteger;
import java.nio.ByteBuffer;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology.AutoOffsetReset;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Serialized;

import de.sfl.prpanic.server.model.avro.ClassIdentifier;
import de.sfl.prpanic.server.model.avro.Project;
import de.sfl.prpanic.server.model.avro.ProjectMetrics;
import de.sfl.prpanic.server.model.avro.TransactionsSnapshot;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;

public class CodeMetricsAggregator {

	public CodeMetricsAggregator(StreamsBuilder builder, StreamsConfig config,
			SpecificAvroSerde<ClassIdentifier> classIdentifierKs, SpecificAvroSerde<TransactionsSnapshot> txSnapshotVs,
			SpecificAvroSerde<ProjectMetrics> projectMetricsVs, SpecificAvroSerde<Project> projectKs) {

		KStream<Project, TransactionsSnapshot> txStream = builTxStream(builder, config, projectKs, txSnapshotVs);
		KTable<Project, ProjectMetrics> projectMetrics = aggregateProjectMetrics(config, projectMetricsVs, projectKs,
				txStream);
		
	}

	private KTable<Project, ProjectMetrics> aggregateProjectMetrics(StreamsConfig config,
			SpecificAvroSerde<ProjectMetrics> projectMetricsVs, SpecificAvroSerde<Project> projectKs,
			KStream<Project, TransactionsSnapshot> txStream) {
		return txStream.through(appendApplicationIdToTopic("project-invocations", config))
				.mapValues(v -> v.getTransactions().size())
				.groupByKey(Serialized.with(projectKs, Serdes.Integer()))
				.aggregate(this::initializeProjectMetrics, this::aggregateProjectMetrics,
						Materialized.with(projectKs, projectMetricsVs));
	}

	private ProjectMetrics initializeProjectMetrics() {
		return ProjectMetrics.newBuilder().setTotalTransactions(ByteBuffer.wrap(BigInteger.ZERO.toByteArray())).build();
	}

	private ProjectMetrics aggregateProjectMetrics(Project key, Integer transactions, ProjectMetrics aggregate) {
		BigInteger previousTx = getTxFromProjectMetrics(aggregate);

		return ProjectMetrics.newBuilder(aggregate)
				.setTotalTransactions(ByteBuffer.wrap(previousTx.add(BigInteger.valueOf(transactions)).toByteArray()))
				.build();
	}

	private BigInteger getTxFromProjectMetrics(ProjectMetrics aggregate) {
		ByteBuffer previousTxBuffer = aggregate.getTotalTransactions();
		byte[] previousTxBytes = new byte[previousTxBuffer.remaining()];
		previousTxBuffer.get(previousTxBytes);
		BigInteger previousTx = new BigInteger(previousTxBytes);
		return previousTx;
	}

	private KStream<Project, TransactionsSnapshot> builTxStream(StreamsBuilder builder, StreamsConfig config,
			SpecificAvroSerde<Project> projectKs, SpecificAvroSerde<TransactionsSnapshot> txSnapshotVs) {

		Consumed<Project, TransactionsSnapshot> consumedMethods = Consumed.with(projectKs, txSnapshotVs)
				.withOffsetResetPolicy(AutoOffsetReset.LATEST);
		return builder.stream(appendApplicationIdToTopic("method-invocations", config), consumedMethods);
	}

	private static String appendApplicationIdToTopic(String topic, StreamsConfig config) {
		return String.format("%s-%s", config.getString(StreamsConfig.APPLICATION_ID_CONFIG));
	}
}
