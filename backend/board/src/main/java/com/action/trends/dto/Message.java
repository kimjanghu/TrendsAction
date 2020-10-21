package com.action.trends.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
	private int id;
	private int boardId;
	private int sendFrom;
	private int sendTo;
	private String authority;
}
