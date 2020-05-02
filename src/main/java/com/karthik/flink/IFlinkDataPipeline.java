package com.karthik.flink;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;

/**
 * 
 * @author karthik
 * Interface for defining your simple DSL for writing streaming ETL jobs in flink 
 * @param <T> Input Type 
 * @param <O> Output type
 */
public interface IFlinkDataPipeline<T, O> {
	
	/**
	 * Return the job name
	 * @return
	 */
	public String getJobName();
	
	/**
	 * Get the broker_host, group_id and topic_name
	 * @return
	 */
	public Tuple3<String, String, String> getSourceKafka();
	
	/**
	 * Get the broker_host and topic_name
	 * @return
	 */
	public Tuple2<String, String> getSinkKafka();
	
	/**
	 * Return a mapper function
	 * @return
	 */
	public abstract MapFunction<T, O> getMapperFunction();
	
	/**
	 * Return a filter function
	 * @return
	 */
	public abstract FilterFunction<T> getFilterFunction();
	
}
