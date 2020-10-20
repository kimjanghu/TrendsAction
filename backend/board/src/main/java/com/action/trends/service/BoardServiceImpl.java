package com.action.trends.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.action.trends.dto.Board;
import com.action.trends.repository.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<Board> getBoardList(int userId) {
		return boardMapper.getBoardList(userId);
	}
	
	@Override
	public int createBoard(int userId, Board board) {
		int result = boardMapper.createBoard(board);
		if (result == 1) {
			int boardId = boardMapper.selectLast();
			int result2 = connectUserBoard(userId, boardId);
			if (result2 != 1) {
				throw new RuntimeException();
			} else 
				return result2;
		} else {
			throw new RuntimeException();
		}
	}
	
	public int connectUserBoard(int userId, int boardId) {
		return boardMapper.connectUserBoard(userId, boardId);
	}
	
	@Override
	public void deleteBoard(int boardId) {
		boardMapper.deleteBoard(boardId);
		boardMapper.deleteUserBoard(boardId);
	}
}
