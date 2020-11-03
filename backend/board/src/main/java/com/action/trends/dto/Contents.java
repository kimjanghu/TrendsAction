package com.action.trends.dto;

import java.util.List;

public class Contents {
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