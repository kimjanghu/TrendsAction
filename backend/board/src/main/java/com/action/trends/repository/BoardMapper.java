package com.action.trends.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.action.trends.dto.Board;
import com.action.trends.dto.News;
import com.action.trends.dto.Sharer;
import com.action.trends.dto.TwittBoard;
import com.action.trends.dto.Twitter;
import com.action.trends.dto.User;

@Mapper
public interface BoardMapper {
	public List<Board> getBoardList(int userId);
	public List<Sharer> getSharerList(int boardId);
	public List<News> getNewsList(int boardId);
	public List<Twitter> getTwitterList(int boardId);
	public int createBoard(Board board);
	public int connectUserBoard(int userId, int boardId);
	public int selectLast();
	public int addTwitts(TwittBoard twittBoard);
	public User searchUser(String email);
	public int updateBoard(int boardId, String name);
	public int updateUserAuth(int userId, int boardId, String authority);
	public int deleteBoard(int boardId);
	public int deleteUserBoard(int boardId);
	public int leaveBoard(int userId, int boardId);
	public int deleteNews(int boardId, int newsId);
	public int deleteTwitts(int boardId, int twitterId);
}
