package com.action.trends.service;

import java.util.List;

import com.action.trends.dto.Board;
import com.action.trends.dto.News;
import com.action.trends.dto.Sharer;
import com.action.trends.dto.Twitter;
import com.action.trends.dto.User;

public interface BoardService {
	public List<Board> getBoardList(int userId);
	public List<Sharer> getSharerList(int boardId);
	public List<News> getNewsList(int boardId);
	public List<Twitter> getTwitterList(int boardId);
	public int createBoard(int userId, Board board);
	public User searchUser(String email);
	public int updateBoard(int boardId, String name);
	public int updateUserAuth(int userId, int boardId, String authority);
	public void deleteBoard(int boardId);
	public int leaveBoard(int userId, int boardId);
}
