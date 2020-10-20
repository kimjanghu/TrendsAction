package com.action.trends.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.action.trends.dto.Board;
import com.action.trends.service.BoardService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@ApiOperation(value="보드 리스트 조회", response = List.class)
	@GetMapping("/board/{userId}")
	public @ResponseBody List<Board> getBoardList(@PathVariable int userId) {
		List<Board> list = new ArrayList<Board>();
		list = boardService.getBoardList(userId);
		
		return list;
	}
	
}
