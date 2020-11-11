package com.action.trends.dto;

public class Trend_Predict {
	private int id;
	private String name;
	private int category_id;
	private String keywords;
	private String description;
	private int year;
	private int month;
	private int week;
	
	public Trend_Predict() {}

	
	
	public Trend_Predict(int id, String name, int category_id, String keywords, String description, int year, int month,
			int week) {
		super();
		this.id = id;
		this.name = name;
		this.category_id = category_id;
		this.keywords = keywords;
		this.description = description;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "Trend_Predict [id=" + id + ", name=" + name + ", category_id=" + category_id + ", keywords=" + keywords
				+ ", description=" + description + ", year=" + year + ", month=" + month + ", week=" + week + "]";
	}


	
	
	

	
}
