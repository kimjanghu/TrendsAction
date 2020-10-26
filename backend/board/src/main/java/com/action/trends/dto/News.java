package com.action.trends.dto;

public class News {
	private int newsId;
	private String title;
	private String content;
	private String thumbnail;
	private String author;
	private String press;
	private String pubDate;
	private String naverLink;
	private String pressLink;
	private int categoryId;
	private int trendId;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public String getNaverLink() {
		return naverLink;
	}
	public void setNaverLink(String naverLink) {
		this.naverLink = naverLink;
	}
	public String getPressLink() {
		return pressLink;
	}
	public void setPressLink(String pressLink) {
		this.pressLink = pressLink;
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
	
	
}
