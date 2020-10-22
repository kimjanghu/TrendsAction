package com.action.trends;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class TrendsApplication {
	private static final String PROPERTIES = "classpath:/mysql.yml" + ",classpath:/aws.yml" + 
					",classpath:/application.properties";
	
	public static void main(String[] args) {
		System.setProperty("spring.config.location", PROPERTIES);
		SpringApplication.run(TrendsApplication.class, args);
	}

}