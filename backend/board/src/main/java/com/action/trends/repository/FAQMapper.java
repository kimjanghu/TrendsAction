package com.action.trends.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.action.trends.dto.FAQ;

@Mapper
public interface FAQMapper {
	public List<FAQ> getFAQ();
}
