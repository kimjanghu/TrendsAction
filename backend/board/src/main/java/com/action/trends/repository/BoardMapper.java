package com.action.trends.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.action.trends.dto.Board;
import com.action.trends.dto.User;

@Mapper
public interface BoardMapper {
	public List<Board> getBoardList(int userId);
	public int createBoard(Board board);
	public int connectUserBoard(int userId, int boardId);
	public int selectLast();
	public int deleteBoard(int boardId);
	public int deleteUserBoard(int boardId);
	public int updateBoard(int boardId, String name);
	public List<User> getSharerList(int boardId);
}
