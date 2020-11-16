package com.action.trends.repository;

import org.apache.ibatis.annotations.Mapper;

import com.action.trends.dto.News;
import com.action.trends.dto.Twitter;

@Mapper
public interface CrawlingMapper {
	int insertNews(News news);

	int insertTwitt(Twitter twitt);
}