package com.netease.hadoop.mapred.config;

import org.apache.hadoop.conf.Configuration;

public class TestConfig {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		conf.addResource("configuration-1.xml");
		System.out.println(conf.get("color"));
		System.out.println(conf.get("size"));
		System.out.println(conf.get("breadth", "wide"));
		/**
		 * 配置合并是被覆盖
		 */
		conf.addResource("configuration-2.xml");
		System.out.println(conf.get("color"));
		System.out.println(conf.get("size"));
		System.out.println(conf.get("breadth", "wide"));
		
		/**
		 * 扩展配置
		 */
		System.out.println(conf.get("size-weight"));
		
	}

}
