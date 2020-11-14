package com.action.trends.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.action.trends.dto.Board;
import com.action.trends.dto.Contents;
import com.action.trends.dto.Message;
import com.action.trends.dto.News;
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
	public List<Board> getBoardListWithAuth(int userId) {
		List<Board> list = boardMapper.getBoardListWithAuth(userId);
		
		return list;
	}
	
	@Override
	public List<Board> getBoardList(int userId) {
		List<Board> list = boardMapper.getBoardList(userId);
		
		for (int i = 0; i < list.size(); i++) {
			Board temp = list.get(i);
			List<NewsList> tempList = getNewsList(temp.getBoardId());
			List<News> tempNewsList = new ArrayList<News>();
			for (int k = 0; k < tempList.size(); k++) {
				News tempNews = new News();
				tempNews.setNewsId(tempList.get(k).getNewsId());
				tempNews.setThumbnail(tempList.get(k).getThumbnail());
				tempNewsList.add(tempNews);
			}
			temp.setNewsList(tempNewsList);
			list.set(i, temp);
		}
		
		return list;
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
	public Contents getContents(int boardId) {
		Contents contents = new Contents();
		Board board = boardMapper.getBoardInfo(boardId);
		contents.setBoardId(boardId);
		contents.setName(board.getName());
		contents.setThumbnail(board.getThumbnail());
		contents.setNewsList(boardMapper.getNewsList(boardId));
		contents.setTwittList(boardMapper.getTwitterList(boardId));
		
		return contents;
	}
	
	@Override
	public List<NewsList> getMostScrappedNewsList(int trendId) {
		return boardMapper.getMostScrappedNewsList(trendId);
	}
	
	@Override
	public List<TwittList> getMostScrappedTwittList(int trendId) {
		return boardMapper.getMostScrappedTwittList(trendId);
	}
	
	@Override
	public List<Message> getMessageList(int userId) {
		return boardMapper.getMessageList(userId);
	}
	
	@Override
	public String getBoardAuth(int userId, int boardId) {
		return boardMapper.getBoardAuth(userId, boardId);
	}
	
	@Override
	public int createBoard(int userId, Board board) {
		String check = boardMapper.checkBoardName(userId, board.getName());
		if (check != null) {
			return -1;
		}
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
		if (boardMapper.checkScrappedTwittAlready(twittBoard) >= 1) {
			return -1;
		}
		boardMapper.plusCountOfTwitt(twittBoard.getTwitterId());
		
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String scrapedDate = format.format(time);
		twittBoard.setScrapedDate(scrapedDate);
		int result = boardMapper.addTwitt(twittBoard);
		return result;
	}
	
	@Override
	public int addNews(NewsBoard newsBoard) {
		if (boardMapper.checkScrappedNewsAlready(newsBoard) >= 1) {
			return -1;
		}
		boardMapper.plusCountOfNews(newsBoard.getNewsId());
		int result = boardMapper.addNews(newsBoard);
		return result;
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
	public int acceptInvite(int messageId, String accepted) {
		boardMapper.updateAccepted(messageId, accepted);
		return boardMapper.acceptInvite(messageId);
	}
	
	@Override
	public int denyInvite(int messageId, String accepted) {
		return boardMapper.updateAccepted(messageId, accepted);
	}
	
	@Override
	public int updateBoard(int boardId, String name, String Email) {
		int userId;
		if (boardMapper.searchUser(Email) == null)
			return -1;
		else
			userId = boardMapper.searchUser(Email).getUserId();
		
		if (userId == 0)
			return -1;
		
		if (!boardMapper.getBoardAuth(userId, boardId).equals("host")) {
			return -1;
		} else {
			return boardMapper.updateBoard(boardId, name);
		}
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
