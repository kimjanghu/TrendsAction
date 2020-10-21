package com.action.trends.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.action.trends.dto.Board;
import com.action.trends.dto.Message;
import com.action.trends.dto.NewsBoard;
import com.action.trends.dto.NewsList;
import com.action.trends.dto.Sharer;
import com.action.trends.dto.TwittBoard;
import com.action.trends.dto.TwittList;
import com.action.trends.dto.User;
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
	public List<Sharer> getSharerList(int boardId) {
		return boardMapper.getSharerList(boardId);
	}
	
	@Override
	public List<NewsList> getNewsList(int boardId) {
		return boardMapper.getNewsList(boardId);
	}
	
	@Override
	public List<TwittList> getTwitterList(int boardId) {
		return boardMapper.getTwitterList(boardId);
	}
	
	@Override
	public List<Message> getMessageList(int userId) {
		return boardMapper.getMessageList(userId);
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
	
	@Override
	public int addTwitt(TwittBoard twittBoard) {
		return boardMapper.addTwitt(twittBoard);
	}
	
	@Override
	public int addNews(NewsBoard newsBoard) {
		return boardMapper.addNews(newsBoard);
	}
	
	@Override
	public User searchUser(String email) {
		return boardMapper.searchUser(email);
	}
	
	@Override
	public int inviteSharer(Message message) {
		return boardMapper.inviteSharer(message);
	}
	
	@Override
	public int updateBoard(int boardId, String name) {
		return boardMapper.updateBoard(boardId, name);
	}
	
	@Override
	public int updateUserAuth(int userId, int boardId, String authority) {
		return boardMapper.updateUserAuth(userId, boardId, authority);
	}
	
	public int connectUserBoard(int userId, int boardId) {
		return boardMapper.connectUserBoard(userId, boardId);
	}
	
	@Override
	public void deleteBoard(int boardId) {
		boardMapper.deleteBoard(boardId);
		boardMapper.deleteUserBoard(boardId);
	}
	
	@Override
	public int leaveBoard(int userId, int boardId) {
		return boardMapper.leaveBoard(userId, boardId);
	}

	@Override
	public int deleteNews(int boardId, int newsId) {
		return boardMapper.deleteNews(boardId, newsId);
	}
	
	@Override
	public int deleteTwitts(int boardId, int twitterId) {
		return boardMapper.deleteTwitts(boardId, twitterId);
	}

}
