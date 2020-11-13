package com.action.trends.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "예측 트렌드 상세 정보를 위한 도메인 객체")
public class TrendPredict {

	@ApiModelProperty(value = "예측 트렌드 ID")
	private int id;

	@ApiModelProperty(value = "예측 트렌드 키워드")
	private String keywords;

	@ApiModelProperty(value = "예측 트렌드 년도")
	private int year;

	@ApiModelProperty(value = "예측 트렌드 월")
	private int month;

	@ApiModelProperty(value = "예측 트렌드 주")
	private int week;

	public TrendPredict() {
	}

	public TrendPredict(int id, String keywords, int year, int month, int week) {
		this.id = id;
		this.keywords = keywords;
		this.year = year;
		this.month = month;
		this.week = week;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	@Override
	public String toString() {
		return "TrendPredict [id=" + id + ", keywords=" + keywords + ", year=" + year + ", month=" + month + ", week="
				+ week + "]";
	}

}
