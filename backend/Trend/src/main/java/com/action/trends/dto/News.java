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

	@ApiModelProperty(value = "뉴스 게시일")
	private String pubDate;

	@ApiModelProperty(value = "뉴스 링크")
	private String link;

	@ApiModelProperty(value = "뉴스 언론사")
	private String press;

	@ApiModelProperty(value = "뉴스 스크랩 개수")
	private String scrapCount;

	public News() {
	}

	public News(int id, int categoryId, int trendId, String title, String thumbnail, String pubDate, String link,
			String press, String scrapCount) {
		this.id = id;
		this.categoryId = categoryId;
		this.trendId = trendId;
		this.title = title;
		this.thumbnail = thumbnail;
		this.pubDate = pubDate;
		this.link = link;
		this.press = press;
		this.scrapCount = scrapCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getScrapCount() {
		return scrapCount;
	}

	public void setScrapCount(String scrapCount) {
		this.scrapCount = scrapCount;
	}

}
