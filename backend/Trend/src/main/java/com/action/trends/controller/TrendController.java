package com.action.trends.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.action.trends.dto.Category;
import com.action.trends.dto.Trend;
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

	@ApiOperation(value = "기존 카테고리 목록 조회")
	@GetMapping("categoryList")
	public ResponseEntity<List<Category>> readCategoryBySelfMade() {
		logger.debug("자체 제작 여부에 따른 카테고리 목록 조회");
		return new ResponseEntity<List<Category>>(service.readCategoryBySelfMade(), HttpStatus.OK);
	}

	@ApiOperation(value = "트렌드 ID로 트렌드 조회")
	@GetMapping("{categoryId}/{trendId}")
	public ResponseEntity<Map<String, Object>> readTrendByTrendId(@PathVariable int categoryId,
			@PathVariable int trendId) {
		logger.debug("트렌드 ID로 트렌드 조회");
		return new ResponseEntity<Map<String, Object>>(service.readTrendByTrendId(categoryId, trendId), HttpStatus.OK);
	}

	@ApiOperation(value = "카테고리별 트렌드 목록 조회")
	@GetMapping("list/{categoryId}")
	public ResponseEntity<List<Trend>> readBycategoryId(@PathVariable int categoryId) {
		logger.debug("카테고리별 트렌드 목록 조회");
		return new ResponseEntity<List<Trend>>(service.readBycategoryId(categoryId), HttpStatus.OK);
	}

	@ApiOperation(value = "해당 트렌드의 최신 뉴스 리스트 조회")
	@GetMapping("news/{trendId}/{page}")
	public ResponseEntity<?> readNewsByTrendId(@PathVariable int trendId, @PathVariable int page) {
		logger.debug("해당 트렌드의 최신 뉴스 리스트 조회");
		return new ResponseEntity<>(service.readNewsByTrendId(trendId, page), HttpStatus.OK);
	}

	@ApiOperation(value = "해당 트렌드의 최신 트위터 리스트 조회")
	@GetMapping("twitter/{trendId}/{page}")
	public ResponseEntity<?> readTwitterByTrendId(@PathVariable int trendId, @PathVariable int page) {
		logger.debug("해당 트렌드의 최신 트위터 리스트 조회");
		return new ResponseEntity<>(service.readTwitterByTrendId(trendId, page), HttpStatus.OK);
	}

	@ApiOperation(value = "해당 년도,월,주의 예측 트렌드 목록 조회")
	@GetMapping("predictTrend/{year}/{month}/{week}")
	public ResponseEntity<List<Map<String, Object>>> readPredictedListByYearMonthWeek(@PathVariable int year,
			@PathVariable int month, @PathVariable int week) {
		logger.debug("해당 년도,월,주의 예측 트렌드 목록 조회");
		return new ResponseEntity<List<Map<String, Object>>>(
				service.readPredictedListByYearMonthWeek(year, month, week), HttpStatus.OK);
	}

	@ApiOperation(value = "해당 년도,월,주,카테고리 ID로 예측 트렌드 조회")
	@GetMapping("predictTrend/{year}/{month}/{week}/{categoryId}")
	public ResponseEntity<Map<String, Object>> readPredictedByYearMonthWeekCategoryId(@PathVariable int year,
			@PathVariable int month, @PathVariable int week, @PathVariable int categoryId) {
		logger.debug("해당 년도,월,주,카테고리 ID로 예측 트렌드 조회");
		return new ResponseEntity<Map<String, Object>>(
				service.readPredictedByYearMonthWeekCategoryId(year, month, week, categoryId), HttpStatus.OK);
	}

	@ApiOperation(value = "기존 카테고리의 모든 트렌드 목록 조회")
	@GetMapping("allTrendsList")
	public ResponseEntity<List<HashMap<String, Object>>> readAllTrendsWhetherNotMade() {
		logger.debug("기존 카테고리의 모든 트렌드 목록 조회");
		return new ResponseEntity<>(service.readAllTrendsNotSelfMade(), HttpStatus.OK);
	}

}
