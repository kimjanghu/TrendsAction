package com.action.trends.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.action.trends.dto.Category;
import com.action.trends.dto.News;
import com.action.trends.dto.Trend;
import com.action.trends.dto.Twitter;
import com.action.trends.repository.TrendRepository;

@Service
public class TrendServiceImpl implements TrendService {

	@Autowired
	TrendRepository repository;

	@Override
	public List<Category> readCategoryBySelfMade(int selfMade) {
		return repository.readCategoryBySelfMade(selfMade);
	}

	@Override
	public List<Trend> readBycategoryId(int categoryId) {
		return repository.readBycategoryId(categoryId);
	}

	@Override
	public List<News> readNewsByTrendId(int trendId) {
		return repository.readNewsByTrendId(trendId);
	}

	@Override
	public List<Twitter> readTwitterByTrendId(int trendId) {
		return repository.readTwitterByTrendId(trendId);
	}

	@Override
	public List<Trend> readPredictedTrend() {
		return repository.readPredictedTrend();
	}

}
