package com.action.trends.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.action.trends.service.TrendService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/trend")
@Api(tags = "Trend", value = "트렌드 컨트롤러")
public class TrendController {
	static final Logger logger = LoggerFactory.getLogger(TrendController.class);

	@Autowired
	TrendService service;

	@ApiOperation(value = "카테고리별 트렌드 목록 조회")
	@GetMapping("{categoryId}")
	public ResponseEntity<?> readBycategoryId(@PathVariable int categoryId) {
		logger.debug("카테고리별 트렌드 목록 조회");
		return new ResponseEntity<>(service.readBycategoryId(categoryId), HttpStatus.OK);
	}

	@ApiOperation(value = "트렌드에 맞는 뉴스 목록 조회")
	@GetMapping("news/{trendId}")
	public ResponseEntity<?> readNewsByTrendId(@PathVariable int trendId) {
		logger.debug("트렌드에 맞는 뉴스 목록 조회");
		return new ResponseEntity<>(service.readNewsByTrendId(trendId), HttpStatus.OK);
	}

	@ApiOperation(value = "트렌드에 맞는 트위터 목록 조회")
	@GetMapping("twitter/{trendId}")
	public ResponseEntity<?> readTwitterByTrendId(@PathVariable int trendId) {
		logger.debug("트렌드에 맞는 트위터 목록 조회");
		return new ResponseEntity<>(service.readTwitterByTrendId(trendId), HttpStatus.OK);
	}

}
