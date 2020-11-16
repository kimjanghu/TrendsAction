package com.action.trends.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.action.trends.dto.Category;
import com.action.trends.dto.News;
import com.action.trends.dto.Trend;
import com.action.trends.dto.Twitter;

@Repository
public interface TrendRepository {
	List<Category> readCategoryBySelfMade();

	Map<String, Object> readTrendByTrendId(int categoryId, int trendId);

	List<Trend> readBycategoryId(int categoryId);

	List<News> readNewsByTrendId(int trendId, int currentPage, int pagePerSize);

	List<Twitter> readTwitterByTrendId(int trendId); // int currentPage, int pagePerSize

	List<Map<String, Object>> readPredictedListByYearMonthWeek(int year, int month, int week);

	Map<String, Object> readPredictedByYearMonthWeekCategoryId(int year, int month, int week, int categoryId);

	List<HashMap<String, Object>> readAllTrendsNotSelfMade();

	Integer getTotalCount(int trendId, String table);
}
