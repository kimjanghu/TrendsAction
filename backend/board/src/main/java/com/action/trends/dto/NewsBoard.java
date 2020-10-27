package com.action.trends.dto;

public class NewsBoard {
	private int boardId;
	private int newsId;
	private int scrapUser;
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public int getScrapUser() {
		return scrapUser;
	}
	public void setScrapUser(int scrapUser) {
		this.scrapUser = scrapUser;
	}
	
	public NewsBoard(int boardId, int newsId, int userId) {
		this.boardId = boardId;
		this.newsId = newsId;
		this.scrapUser = userId;
	}
}
