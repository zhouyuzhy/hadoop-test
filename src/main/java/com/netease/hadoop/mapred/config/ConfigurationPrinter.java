package com.netease.hadoop.mapred.config;

import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ConfigurationPrinter extends Configured implements Tool {

	static{
		Configuration.addDefaultResource("hdfs-default.xml");
		Configuration.addDefaultResource("hdfs-site.xml");
		Configuration.addDefaultResource("mapred-default.xml");
		Configuration.addDefaultResource("mapred-site.xml");
	}
	
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
		for(Entry<String, String> entry: conf){
			System.out.printf("%s=%s\n", entry.getKey(), entry.getValue());
		}
		
		return 0;
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/**
		 * 在第一个configuration.set的时候会load resources
		 * 而resources路径在类加载时执行静态块add到Configuration类的常量数组中
		 * 在org.apache.hadoop.util.GenericOptionsParser.GenericOptionsParser.processGeneralOptions中接续命令行参数
		 */
		int exitCode = ToolRunner.run(new ConfigurationPrinter(), args);
		System.exit(exitCode);
	}

}
