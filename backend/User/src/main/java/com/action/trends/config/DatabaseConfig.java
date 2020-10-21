package com.action.trends.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
		basePackages = "com.action.trends.repository"
)
public class DatabaseConfig {}

