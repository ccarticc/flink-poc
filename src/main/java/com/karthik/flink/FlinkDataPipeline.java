package com.karthik.flink;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;

/**
 * Base class for defining a Flink pipeline
 * @author kdeivas
 *
 */
public class FlinkDataPipeline implements IFlinkDataPipeline<String, String> {

	@Override
	public String getJobName() {
		return "Test";
	}

	@Override
	public Tuple3<String, String, String> getSourceKafka() {
		return Tuple3.of("localhost:9092", "test_group_id", "source_topic");
	}

	@Override
	public Tuple2<String, String> getSinkKafka() {
		return Tuple2.of("localhost:9092", "sink_topic");
	}

	@Override
	public MapFunction<String, String> getMapperFunction() {
		
		return new MapFunction<String, String>() {

			@Override
			public String map(String arg0) throws Exception {
				System.err.println("Got input " + arg0);
				return arg0.toUpperCase();
			}
		};
	}

	@Override
	public FilterFunction<String> getFilterFunction() {
		return new FilterFunction<String>() {
			@Override
			public boolean filter(String arg0) throws Exception {
				return !arg0.equals("skip");
			}
			
		};
	}

}
