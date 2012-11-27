package com.netease.hadoop.mapper;

import static org.mockito.Mockito.*;
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;

public class MaxTemperatureMapperTest extends TestCase{

	public void testProcessValidRecord() throws IOException {
		MaxTemperatureMapper mapper = new MaxTemperatureMapper();
		Text value = new Text("0043011990999991950051518004+68750+023550FM-12+0382" + 
							"99999V0203201N00261220001CN9999999N9-00111+99999999999");
		//mock使用cglib动态代理构造一个代理对象。
		//handler在运行前记录运行的状态，主要是方法和参数
		//verify状态改变后，即获取先前的运行状态与当前的运行状态对比
		OutputCollector<Text, IntWritable> output = 
				mock(OutputCollector.class);
		mapper.map(null, value, output, null);
		verify(output).collect(new Text("1950"), new IntWritable(-11));
	}
	
	public void testMissingTemperatureRecord() throws IOException{
		MaxTemperatureMapper mapper = new MaxTemperatureMapper();
		Text value = new Text("0043011990999991950051518004+68750+023550FM-12+0382" + 
				"99999V0203201N00261220001CN9999999N9+99991+99999999999");
		OutputCollector<Text, IntWritable> output = mock(OutputCollector.class);
		mapper.map(null, value, output, null);
		verify(output, never()).collect(any(Text.class), any(IntWritable.class));
	}

}
