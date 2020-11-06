package com.action.trends.filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Component
public class FilterConfig {

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		List<String> allowed = new ArrayList<String>();
		allowed.add("*");
		config.setAllowedOrigins(allowed);
		config.setAllowedHeaders(allowed);
		config.setAllowedMethods(allowed);
		source.registerCorsConfiguration("*", config);
		return new CorsFilter();
	}

}
