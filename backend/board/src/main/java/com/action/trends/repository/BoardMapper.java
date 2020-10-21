package com.action.trends.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.action.trends.dto.Board;
import com.action.trends.dto.Message;
import com.action.trends.dto.NewsBoard;
import com.action.trends.dto.NewsList;
import com.action.trends.dto.Sharer;
import com.action.trends.dto.TwittBoard;
import com.action.trends.dto.TwittList;
import com.action.trends.dto.User;

@Mapper
public interface BoardMapper {
	public List<Board> getBoardList(int userId);
	public List<Sharer> getSharerList(int boardId);
	public List<NewsList> getNewsList(int boardId);
	public List<TwittList> getTwitterList(int boardId);
	public List<Message> getMessageList(int userId);
	public String getCategoryName(int categoryId);
	public String getTrendName(int trendid);
	public int createBoard(Board board);
	public int connectUserBoard(int userId, int boardId);
	public int selectLast();
	public int addTwitt(TwittBoard twittBoard);
	public int addNews(NewsBoard newsBoard);
	public User searchUser(String email);
	public int inviteSharer(Message message);
	public int acceptInvite(int messageId);
	public int updateBoard(int boardId, String name);
	public int updateUserAuth(int userId, int boardId, String authority);
	public int updateAccepted(int messageId, String accepted);
	public int deleteBoard(int boardId);
	public int deleteUserBoard(int boardId);
	public int leaveBoard(int userId, int boardId);
	public int deleteNews(int boardId, int newsId);
	public int deleteTwitts(int boardId, int twitterId);
}
