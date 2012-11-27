package com.netease.hadoop.reduce;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import junit.framework.TestCase;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;


public class TestMaxTemperatureReduer extends TestCase{

	public void testReturnMaxIntWritable() throws IOException{
		MaxTemperatureReducer reducer = new MaxTemperatureReducer();
		Iterator<IntWritable> values = Arrays.asList(
				new IntWritable(10), new IntWritable(5)
				).iterator();
		OutputCollector<Text, IntWritable> output = mock(OutputCollector.class);
		reducer.reduce(null, values, output, null);
		verify(output).collect(null, new IntWritable(10));
	}
}
