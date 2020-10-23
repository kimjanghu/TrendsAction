package com.action.trends.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "트위터 상세 정보를 위한 도메인 객체")
public class Twitter {

	@ApiModelProperty(value = "트위터 ID")
	private int id;

	@ApiModelProperty(value = "카테고리 ID")
	private String categoryId;

	@ApiModelProperty(value = "트렌드 ID")
	private int trendId;

	@ApiModelProperty(value = "트위터 작성자")
	private String author;

	@ApiModelProperty(value = "트위터 내용")
	private String content;

	@ApiModelProperty(value = "트위터 업로드 날짜")
	private String date;

	public Twitter() {
	}

	public Twitter(int id, String categoryId, int trendId, String author, String content, String date) {
		this.id = id;
		this.categoryId = categoryId;
		this.trendId = trendId;
		this.author = author;
		this.content = content;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public int getTrendId() {
		return trendId;
	}

	public void setTrendId(int trendId) {
		this.trendId = trendId;
	}

}
