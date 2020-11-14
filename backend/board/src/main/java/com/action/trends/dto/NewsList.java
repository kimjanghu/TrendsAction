package com.action.trends.dto;

public class NewsList {
	private int newsId;
	private String title;
	private String thumbnail;
	private String press;
	private String pubDate;
	private String link;
	private int categoryId;
	private int trendId;
	
	private String categoryName;
	private String trendName;
	private int scrapUser;
	private String nickname;
	private String profile;
	private int scrapCount;
	private String scrapedDate;
	public String getScrapedDate() {
		return scrapedDate;
	}
	public void setScrapedDate(String scrapedDate) {
		this.scrapedDate = scrapedDate;
	}
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getTrendId() {
		return trendId;
	}
	public void setTrendId(int trendId) {
		this.trendId = trendId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getTrendName() {
		return trendName;
	}
	public void setTrendName(String trendName) {
		this.trendName = trendName;
	}
	public int getScrapUser() {
		return scrapUser;
	}
	public void setScrapUser(int scrapUser) {
		this.scrapUser = scrapUser;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public int getScrapCount() {
		return scrapCount;
	}
	public void setScrapCount(int scrapCount) {
		this.scrapCount = scrapCount;
	}
	
	
}
