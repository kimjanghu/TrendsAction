package com.action.trends.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
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
}
