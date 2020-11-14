package com.action.trends.dto;

public class News {
	private String title;
	private String press;
	private	String link;
	private String date;
	private String thumbnail;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public News(String title, String press, String link, String date, String thumbnail) {
		super();
		this.title = title;
		this.press = press;
		this.link = link;
		this.date = date;
		this.thumbnail = thumbnail;
	}
}
