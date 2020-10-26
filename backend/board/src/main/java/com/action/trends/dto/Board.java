package com.action.trends.dto;

public class Board {
	private int boardId;
	private String name;
	private String thumbnail;
	
	public Board(String name, String thumbnail) {
		this.name = name;
		this.thumbnail = thumbnail;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
}
