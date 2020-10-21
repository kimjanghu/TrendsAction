package com.action.trends.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsList {
	private int newsId;
	private String title;
	private String content;
	private String thumbnail;
	private String author;
	private String press;
	private String pubDate;
	private String naverLink;
	private String pressLink;
	private int categoryId;
	private int trendId;
	
	private String categoryName;
	private String trendName;
	private int scrapUser;
	private String nickname;
	private String profile;
}
