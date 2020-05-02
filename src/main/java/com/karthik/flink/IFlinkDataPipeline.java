package com.karthik.flink;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;

/**
 * 
 * @author kdeivas
 *
 * @param <T> Input Type 
 * @param <O> Output type
 */
public interface IFlinkDataPipeline<T, O> {
	
	public String getJobName();
	
	public Tuple3<String, String, String> getSourceKafka();
	
	public Tuple2<String, String> getSinkKafka();
	
	public abstract MapFunction<T, O> getMapperFunction();
	
	public abstract FilterFunction<T> getFilterFunction();
	
}
