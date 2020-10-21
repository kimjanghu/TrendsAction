package com.action.trends.service;

import java.util.List;

import com.action.trends.dto.Trend;

public interface TrendService {
	List<Trend> readBycategoryId(int categoryId);
}