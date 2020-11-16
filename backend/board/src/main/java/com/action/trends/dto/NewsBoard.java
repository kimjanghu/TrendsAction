package com.action.trends.dto;

public class NewsBoard {
	private int boardId;
	private int newsId;
	private int scrapUser;
	private String scrapedDate;

	public NewsBoard() {
	}

	public NewsBoard(int boardId, int newsId, int scrapUser, String scrapedDate) {
		this.boardId = boardId;
		this.newsId = newsId;
		this.scrapUser = scrapUser;
		this.scrapedDate = scrapedDate;
	}

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

	public String getScrapedDate() {
		return scrapedDate;
	}

	public void setScrapedDate(String scrapedDate) {
		this.scrapedDate = scrapedDate;
	}

}
