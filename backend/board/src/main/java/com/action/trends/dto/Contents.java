package com.action.trends.dto;

import java.util.List;

public class Contents {
	private int boardId;
	private String name;
	private String thumbnail;
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	private List<NewsList> newsList;
	private List<TwittList> twittList;
	
	public List<NewsList> getNewsList() {
		return newsList;
	}
	public void setNewsList(List<NewsList> newsList) {
		this.newsList = newsList;
	}
	public List<TwittList> getTwittList() {
		return twittList;
	}
	public void setTwittList(List<TwittList> twittList) {
		this.twittList = twittList;
	}
}