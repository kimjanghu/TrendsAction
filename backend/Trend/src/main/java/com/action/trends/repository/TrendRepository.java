package com.action.trends.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.action.trends.dto.Trend;

@Repository
public interface TrendRepository {
	List<Trend> readBycategoryId(int categoryId);
}
