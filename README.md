# flink poc : repo for experiments on flink

## Writing templatized flink jobs
* Runner.java 
  Top level flink runner. 
* IFlinkDataPipeline
  Interface used to define a simple DSL for your data transformation jobs
  Reads from kafka and writes back to kafka
  implement your transformation in the mapper and filter out record as needed.
* FlinkDataPipeline
  Sample implementation

