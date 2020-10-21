package com.action.trends.service;

import java.util.List;

import com.action.trends.dto.News;
import com.action.trends.dto.Trend;
import com.action.trends.dto.Twitter;

public interface TrendService {
	List<Trend> readBycategoryId(int categoryId);
	
	List<News> readNewsByTrendId(int trendId);
	
	List<Twitter> readTwitterByTrendId(int trendId);
}
