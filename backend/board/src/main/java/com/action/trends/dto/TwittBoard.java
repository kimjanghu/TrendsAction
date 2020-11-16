package com.action.trends.dto;

public class TwittBoard {
	private int boardId;
	private int twitterId;
	private int scrapUser;
	private String scrapedDate;

	public String getScrapedDate() {
		return scrapedDate;
	}

	public void setScrapedDate(String scrapedDate) {
		this.scrapedDate = scrapedDate;
	}

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

}
