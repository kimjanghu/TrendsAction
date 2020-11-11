package com.action.trends.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.action.trends.dto.Category;
import com.action.trends.dto.News;
import com.action.trends.dto.Trend;
import com.action.trends.dto.Trend_Predict;
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
	public Trend readTrendByTrendId(int trendId) {
		return repository.readTrendByTrendId(trendId);
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
	public Map<String, Object> readPredictedTrend(int year, int month, int week, int categoryId) {
		
			Trend_Predict predictData = repository.readPredictedTrend(year, month, week, categoryId);
			Map<String, Object> bigMap = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println(predictData.toString());
			
			if(predictData.getName() != null && predictData.getDescription() != null) {
				map.put("id", predictData.getId());
				map.put("name", predictData.getName());
//				map.put("categoryId", predictData.getCategory_id());
				map.put("categoryId", categoryId);
				map.put("keywords", predictData.getKeywords());
				map.put("description", predictData.getDescription());
				map.put("year", predictData.getYear());
				map.put("month", predictData.getMonth());
				map.put("week", predictData.getYear());
				bigMap.put("data", map);
				bigMap.put("message", "트렌드 단어와 트렌드 설명이 제대로 들어있습니다.");
				bigMap.put("status", true);
			}else {
				map.put("id", predictData.getId());
				map.put("name", predictData.getName());
//				map.put("categoryId", predictData.getCategory_id());
				map.put("categoryId", categoryId);
				map.put("keywords", predictData.getKeywords());
				map.put("description", predictData.getDescription());
				map.put("year", predictData.getYear());
				map.put("month", predictData.getMonth());
				map.put("week", predictData.getYear());
				bigMap.put("data", map);
				bigMap.put("message", "트렌드 단어와 트렌드 설명 추가가 필요합니다");
				bigMap.put("status", false);
			}
		
		
		return bigMap;
	}

	@Override
	public List<HashMap<String, Object>> readAllTrendsWhetherSelfMadeIs(int selfMade) {
		return getJson(new HashMap<String, Object>(), repository.readAllTrendsWhetherSelfMadeIs(selfMade));
	}

	private List<HashMap<String, Object>> getJson(HashMap<String, Object> map, List<HashMap<String, Object>> list) {
		List<HashMap<String, Object>> results = new ArrayList<>();
		HashMap<String, Object> category = new HashMap<String, Object>();
		List<HashMap<String, Object>> trend = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> detail;

		String categoryName = (String) list.get(0).get("categoryName");
		for (int i = 0, size = list.size(); i < size; i++) {
			detail = new HashMap<String, Object>();
			map = list.get(i);

			if (!categoryName.equals((String) map.get("categoryName"))) { // 카테고리명이 바뀌면
				trendAppendToCategory(category, (String) categoryName, trend); // 트렌드 => 카테고리
				results.add(category); // 카테고리 => 결과
				trend = new ArrayList<HashMap<String, Object>>(); // 초기화
				category = new HashMap<String, Object>();
				categoryName = (String) map.get("categoryName"); // 카테고리명 변경
			}

			detail.put("trendName", map.get("trendName")); // 상세 내용
			detail.put("trendId", map.get("trendId"));
			trend.add(detail); // 상세 내용 => 트렌드

		}
		trendAppendToCategory(category, (String) categoryName, trend);
		results.add(category);

		return results;
	}

	private void trendAppendToCategory(HashMap<String, Object> category, String categoryName,
			List<HashMap<String, Object>> trend) {
		category.put("categoryName", categoryName);
		category.put(categoryName, trend);
	}

}
