package com.action.trends.service;

import java.util.List;

import com.action.trends.dto.News;
import com.action.trends.dto.Trend;

public interface TrendService {
	List<Trend> readBycategoryId(int categoryId);

	List<News> readNewsByTrendId(int trendId);
}
