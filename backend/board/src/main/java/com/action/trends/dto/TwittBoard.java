package com.action.trends.dto;

public class TwittBoard {
	private int boardId;
	private int twitterId;
	private int scrapUser;
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getTwitterId() {
		return twitterId;
	}
	public void setTwitterId(int twitterId) {
		this.twitterId = twitterId;
	}
	public int getScrapUser() {
		return scrapUser;
	}
	public void setScrapUser(int scrapUser) {
		this.scrapUser = scrapUser;
	}
	
	public TwittBoard(int boardId, int twitterId, int userId) {
		this.boardId = boardId;
		this.twitterId = twitterId;
		this.scrapUser = userId;
	}
}
