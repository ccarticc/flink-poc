package com.karthik.flink;

import java.util.Properties;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

/**
 * 
 * @author karthik
 * This class is generic Job Runner
 */
public class Runner {

	public static void main(String[] args) throws Exception {
		// set up the streaming execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		//TODO make this configurable get the class information from a config file
		IFlinkDataPipeline<String, String> pipeline = new FlinkDataPipeline();
		String jobName = pipeline.getJobName();
		
		Tuple3<String, String, String> sourceTuple = pipeline.getSourceKafka();
		Tuple2<String, String> sinkTuple = pipeline.getSinkKafka();

		//simple predefined DAG source -> filter -> map -> sink 
		FlinkKafkaConsumer<String> source = getKafkaSource(sourceTuple);
		FlinkKafkaProducer<String> sink = getKafkaSink(sinkTuple);
		env
			.addSource(source)
			.filter(pipeline.getFilterFunction())
			.map(pipeline.getMapperFunction())
			.addSink(sink);
		//run the job
		env.execute(jobName);
	}

	/**
	 * Get the kafka sink information and create a flink kafka consumer
	 * @param sinkTuple
	 * @return
	 */
	private static FlinkKafkaProducer<String> getKafkaSink(Tuple2<String, String> sinkTuple) {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", sinkTuple.f0);				
		return new FlinkKafkaProducer<>(
				sinkTuple.f0,
				sinkTuple.f1,
				new SimpleStringSchema());	
	}

	/**
	 * Get the kafka source information and create a flink kafka producer
	 * @param sourceTuple
	 * @return
	 */
	private static FlinkKafkaConsumer<String> getKafkaSource(Tuple3<String, String, String> sourceTuple) {
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", sourceTuple.f0);
		properties.setProperty("group.id", sourceTuple.f1);
		FlinkKafkaConsumer<String> source = new FlinkKafkaConsumer<>(sourceTuple.f2, new SimpleStringSchema(), properties);
		return source;
	}
	
}
