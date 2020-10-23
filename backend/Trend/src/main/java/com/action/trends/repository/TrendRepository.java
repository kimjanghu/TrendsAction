package com.action.trends.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.action.trends.dto.Category;
import com.action.trends.dto.News;
import com.action.trends.dto.Trend;
import com.action.trends.dto.Twitter;

@Repository
public interface TrendRepository {
	List<Category> readCategoryBySelfMade(int selfMade);
	
	List<Trend> readBycategoryId(int categoryId);

	List<News> readNewsByTrendId(int trendId);

	List<Twitter> readTwitterByTrendId(int trendId);

	List<Trend> readPredictedTrend();
}