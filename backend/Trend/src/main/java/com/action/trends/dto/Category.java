package com.action.trends.dto;

public class Category {
	private int id;

	private String name;

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
