package com.karthik.flink;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;

public abstract class AbstractMapFunction extends RichMapFunction {
	
	//private Connection dbconn;
	
	public void open(Configuration parameters) {
		//dbconn = new DBConnection();
    }
	
	public void close() {
		//if (dbconn != null) dbconn.close();
	}
}
