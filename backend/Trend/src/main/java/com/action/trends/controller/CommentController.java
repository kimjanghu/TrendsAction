package com.action.trends.controller;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.action.trends.dto.Comment;
import com.action.trends.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/comment")
@Api(tags = "Comment", value = "댓글 컨트롤러")
public class CommentController {
	static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	CommentService service;
	
	@ApiOperation(value = "해당 트렌드의 댓글 리스트 조회")
	@GetMapping("{trendId}")
	public ResponseEntity<?> readByTrendId(@PathVariable int trendId) {
		logger.debug("해당 트렌드의 댓글 리스트 조회");
		System.out.println(service.readByTrendId(trendId));
		return new ResponseEntity<>(service.readByTrendId(trendId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 트렌드에 댓글 삽입")
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Comment comment) {
		logger.debug("해당 트렌드에 댓글 삽입");
		String date = LocalDateTime.now() + "";
		comment.setDate(date);
		service.insert(comment);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "해당 트렌드의 댓글 수정")
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Comment comment) {
		logger.debug("해당 트렌드의 댓글 수정");
		String date = LocalDateTime.now() + "";
		comment.setDate(date);
		service.update(comment);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "해당 트렌드의 댓글 삭제")
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		logger.debug("해당 트렌드의 댓글 삭제");
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
