package com.action.trends.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "댓글 상세 정보를 위한 도메인 객체")
public class Comment {

	@ApiModelProperty(value = "댓글 ID")
	private int id;

	@ApiModelProperty(value = "유저 ID")
	private int userId;

	@ApiModelProperty(value = "트렌드 ID")
	private int trendId;

	@ApiModelProperty(value = "댓글 내용")
	private String content;

	@ApiModelProperty(value = "댓글 게시일")
	private String date;

	public Comment() {
	}

	public Comment(int id, int userId, int trendId, String content, String date) {
		this.id = id;
		this.userId = userId;
		this.trendId = trendId;
		this.content = content;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTrendId() {
		return trendId;
	}

	public void setTrendId(int trendId) {
		this.trendId = trendId;
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

}
