package com.action.trends.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "트렌드 상세 정보를 위한 도메인 객체")
public class Trend {

	@ApiModelProperty(value = "트렌드 ID")
	private int id;

	@ApiModelProperty(value = "카테고리 ID")
	private String categoryId;

	@ApiModelProperty(value = "트렌드 이름")
	private String name;

	@ApiModelProperty(value = "클래스 예측 여부")
	private String predicted;

	public Trend() {
	}

	public Trend(int id, String categoryId, String name, String predicted) {
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.predicted = predicted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getPredicted() {
		return predicted;
	}

	public void setPredicted(String predicted) {
		this.predicted = predicted;
	}

	@Override
	public String toString() {
		return "Trend [id=" + id + ", categoryId=" + categoryId + ", name=" + name + ", predicted=" + predicted + "]";
	}

}
