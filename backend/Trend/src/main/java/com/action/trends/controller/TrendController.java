package com.action.trends.controller;

import java.util.HashMap;
import java.util.List;

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
import com.action.trends.dto.News;
import com.action.trends.dto.Trend;
import com.action.trends.dto.Twitter;
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

	@ApiOperation(value = "자체 제작 여부에 따른 카테고리 목록 조회")
	@GetMapping("category/{selfMade}")
	public ResponseEntity<List<Category>> readCategoryBySelfMade(@PathVariable int selfMade) {
		logger.debug("자체 제작 여부에 따른 카테고리 목록 조회");
		return new ResponseEntity<List<Category>>(service.readCategoryBySelfMade(selfMade), HttpStatus.OK);
	}

	@ApiOperation(value = "트렌드 ID로 트렌드 조회")
	@GetMapping("{trendId}")
	public ResponseEntity<Trend> readTrendByTrendId(@PathVariable int trendId) {
		logger.debug("트렌드 ID로 트렌드 조회");
		return new ResponseEntity<Trend>(service.readTrendByTrendId(trendId), HttpStatus.OK);
	}

	@ApiOperation(value = "카테고리별 트렌드 목록 조회")
	@GetMapping("list/{categoryId}")
	public ResponseEntity<List<Trend>> readBycategoryId(@PathVariable int categoryId) {
		logger.debug("카테고리별 트렌드 목록 조회");
		return new ResponseEntity<List<Trend>>(service.readBycategoryId(categoryId), HttpStatus.OK);
	}

	@ApiOperation(value = "해당 트렌드의 최신 뉴스 리스트 조회")
	@GetMapping("news/{trendId}")
	public ResponseEntity<List<News>> readNewsByTrendId(@PathVariable int trendId) {
		logger.debug("해당 트렌드의 최신 뉴스 리스트 조회");
		return new ResponseEntity<List<News>>(service.readNewsByTrendId(trendId), HttpStatus.OK);
	}

	@ApiOperation(value = "해당 트렌드의 최신 트위터 리스트 조회")
	@GetMapping("twitter/{trendId}")
	public ResponseEntity<List<Twitter>> readTwitterByTrendId(@PathVariable int trendId) {
		logger.debug("해당 트렌드의 최신 트위터 리스트 조회");
		return new ResponseEntity<List<Twitter>>(service.readTwitterByTrendId(trendId), HttpStatus.OK);
	}

	@ApiOperation(value = "예측 트렌드 키워드 목록 조회")
	@GetMapping("predictTrend")
	public ResponseEntity<List<Trend>> readPredictedTrend() {
		logger.debug("예측 트렌드 키워드 목록 조회");
		return new ResponseEntity<List<Trend>>(service.readPredictedTrend(), HttpStatus.OK);
	}

	@ApiOperation(value = "예측 / 기존 카테고리 여부에 따른 카테고리의 모든 트렌드 목록 조회")
	@GetMapping("allTrendsList/{selfMade}")
	public ResponseEntity<List<HashMap<String, Object>>> readAllTrendsWhetherSelfMadeIs(@PathVariable int selfMade) {
		logger.debug("예측 / 기존 카테고리 여부에 따른 카테고리의 모든 트렌드 목록 조회");
		return new ResponseEntity<>(service.readAllTrendsWhetherSelfMadeIs(selfMade), HttpStatus.OK);
	}

}
