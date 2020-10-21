package com.action.trends.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwittList {
	private int twitterId;
	private String content;
	private String author;
	private String date;
	private int categoryId;
	private int trendId;
	
	private String categoryName;
	private String trendName;
	private int scrapUser;
	private String nickname;
	private String profile;
}
