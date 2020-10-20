package com.action.trends.service;

import java.util.List;
import com.action.trends.dto.Board;

public interface BoardService {
	public List<Board> getBoardList(int userId);
	public int createBoard(int userId, Board board);
	public void deleteBoard(int boardId);
}
