package com.action.trends.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Twitter {
	private int twitterId;
	private String content;
	private String author;
	private String date;
	private int categoryId;
	private int trendId;
}
