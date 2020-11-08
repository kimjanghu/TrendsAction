package com.action.trends.repository;

import org.apache.ibatis.annotations.Mapper;

import com.action.trends.dto.Twitter;

@Mapper
public interface CrawlingMapper {
	int insertTwitt(Twitter twitt);
}