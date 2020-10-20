package com.action.trends.service;

import java.util.List;

import com.action.trends.dto.Board;
import com.action.trends.dto.User;

public interface BoardService {
	public List<Board> getBoardList(int userId);
	public List<User> getSharerList(int boardId);
	public int createBoard(int userId, Board board);
	public void deleteBoard(int boardId);
	public int updateBoard(int boardId, String name);
	public User searchUser(String email);
}
