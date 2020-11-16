package com.action.trends.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "카테고리 상세 정보를 위한 도메인 객체")
public class Category {

	@ApiModelProperty(value = "카테고리 ID")
	private int id;

	@ApiModelProperty(value = "카테고리 이름")
	private String name;

	@ApiModelProperty(value = "카테고리 자체 제작 여부")
	private int selfMade;

	public Category() {
	}

	public Category(int id, String name, int selfMade) {
		this.id = id;
		this.name = name;
		this.selfMade = selfMade;
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

	public int isSelfMade() {
		return selfMade;
	}

	public void setSelfMade(int selfMade) {
		this.selfMade = selfMade;
	}

}
