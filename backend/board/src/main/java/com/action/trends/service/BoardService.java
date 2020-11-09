package com.action.trends.service;

import java.util.List;

import com.action.trends.dto.Board;
import com.action.trends.dto.Contents;
import com.action.trends.dto.Message;
import com.action.trends.dto.NewsBoard;
import com.action.trends.dto.NewsList;
import com.action.trends.dto.Sharer;
import com.action.trends.dto.TwittBoard;
import com.action.trends.dto.TwittList;
import com.action.trends.dto.User;

public interface BoardService {
	public List<Board> getBoardList(int userId);
	public List<Sharer> getSharerList(int boardId);
	public List<NewsList> getNewsList(int boardId);
	public List<TwittList> getTwitterList(int boardId);
	public Contents getContents(int boardId);
	public List<NewsList> getMostScrappedNewsList(int trendId);
	public List<TwittList> getMostScrappedTwittList(int trendId);
	public List<Message> getMessageList(int userId);
	public String getBoardAuth(int userId, int boardId);
	public int createBoard(int userId, Board board);
	public int addTwitt(TwittBoard twittBoard);
	public int addNews(NewsBoard newsBoard);
	public User searchUser(String email);
	public int inviteSharer(Message message);
	public int acceptInvite(int messageId, String accepted);
	public int updateBoard(int boardId, String name);
	public int updateUserAuth(int userId, int boardId, String authority);
	public void deleteBoard(int boardId);
	public int leaveBoard(int userId, int boardId);
	public int deleteNews(int boardId, int newsId);
	public int deleteTwitts(int boardId, int twitterId);
}
