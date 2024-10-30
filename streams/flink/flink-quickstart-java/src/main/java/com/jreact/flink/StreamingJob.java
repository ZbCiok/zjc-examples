package com.jreact.flink;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.aggregation.Aggregations;
import org.apache.flink.api.java.tuple.Tuple2;


public class StreamingJob {

	public static void main(String[] args) throws Exception {
		final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
	    
	    
	    DataSet<String> text = env.readTextFile("flink.txt");
	    
	    DataSet<Tuple2<String, Integer>> counts = 
	        text.flatMap(new LineSplitter())
	        .groupBy(0)
	        .aggregate(Aggregations.SUM, 1);

	    counts.print();

	    
	    // execute program
	    //env.execute("WordCount Example");
	}
}