package com.action.trends.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.action.trends.dto.FAQ;
import com.action.trends.repository.FAQMapper;

@Service
public class FAQServiceImpl implements FAQService {

	@Autowired
	FAQMapper faqMapper;

	public List<FAQ> getFAQ() {
		return faqMapper.getFAQ();
	}
}
