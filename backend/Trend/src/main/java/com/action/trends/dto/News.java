package com.action.trends.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "뉴스 상세 정보를 위한 도메인 객체")
public class News {

	@ApiModelProperty(value = "뉴스 ID")
	private int id;

	@ApiModelProperty(value = "카테고리 ID")
	private int categoryId;

	@ApiModelProperty(value = "트렌드 ID")
	private int trendId;

	@ApiModelProperty(value = "뉴스 제목")
	private String title;

	@ApiModelProperty(value = "뉴스 썸네일")
	private String thumbnail;

	@ApiModelProperty(value = "뉴스 내용")
	private String content;

	@ApiModelProperty(value = "뉴스 게시일")
	private String pubDate;

	@ApiModelProperty(value = "뉴스 네이버 링크")
	private String naverLink;

	@ApiModelProperty(value = "뉴스 언론사")
	private String press;

	@ApiModelProperty(value = "뉴스 언론사 링크")
	private String pressLink;

	@ApiModelProperty(value = "뉴스 기자")
	private String author;

	public News() {
	}

	public News(int id, int categoryId, int trendId, String title, String thumbnail, String content, String pubDate,
			String naverLink, String press, String pressLink, String author) {
		this.id = id;
		this.categoryId = categoryId;
		this.trendId = trendId;
		this.title = title;
		this.thumbnail = thumbnail;
		this.content = content;
		this.pubDate = pubDate;
		this.naverLink = naverLink;
		this.press = press;
		this.pressLink = pressLink;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getPressLink() {
		return pressLink;
	}

	public void setPressLink(String pressLink) {
		this.pressLink = pressLink;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
