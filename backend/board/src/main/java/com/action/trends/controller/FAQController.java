package com.action.trends.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.action.trends.dto.FAQ;
import com.action.trends.service.FAQService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class FAQController {

	@Autowired
	private FAQService faqService;

	@ApiOperation(value = "FAQ 조회")
	@GetMapping("/faq")
	public ResponseEntity<Map<String, Object>> getfaq() {
		ResponseEntity<Map<String, Object>> entity = null;
		List<FAQ> list = new ArrayList<FAQ>();
		Map<String, Object> resultMap = new HashMap<>();

		try {
			list = faqService.getFAQ();

			resultMap.put("status", true);
			if (list.size() == 0) {
				resultMap.put("message", "FAQ가 없습니다.");
			} else {
				resultMap.put("message", "FAQ 리스트 조회에 성공했습니다.");
			}
			resultMap.put("data", list);
			entity = new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}

		return entity;
	}

	// ------------------- 예외처리 -----------------------

	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", true);
		resultMap.put("message", data);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	private ResponseEntity<Map<String, Object>> handleException(Exception e) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", false);
		resultMap.put("message", e.getMessage());
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
