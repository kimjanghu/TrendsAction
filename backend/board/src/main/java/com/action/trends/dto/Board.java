package com.action.trends.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private int boardId;
	private String name;
	private String thumbnail;
	
	public Board(String name, String thumbnail) {
		this.name = name;
		this.thumbnail = thumbnail;
	}
}
