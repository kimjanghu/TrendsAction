package com.action.trends.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.action.trends.dto.Trend;
import com.action.trends.repository.TrendRepository;

@Service
public class TrendServiceImpl implements TrendService {

	@Autowired
	TrendRepository repository;

	@Override
	public List<Trend> readBycategoryId(int categoryId) {
		return repository.readBycategoryId(categoryId);
	}

}
