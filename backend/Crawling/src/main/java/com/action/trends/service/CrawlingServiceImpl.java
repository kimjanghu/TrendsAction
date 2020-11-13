package com.action.trends.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.action.trends.dto.News;
import com.action.trends.dto.Twitter;
import com.action.trends.repository.CrawlingMapper;

@Service
public class CrawlingServiceImpl implements CrawlingService {
	
	@Autowired
	CrawlingMapper crawlingMapper;
	
	@Override
	public int insertNews(Object obj) {
		JSONObject jsonObject = (JSONObject) obj;
		
		Object titleO = jsonObject.get("title");
		JSONObject titleJO = (JSONObject) titleO;
		// 가져온 뉴스 개수
		int size = titleJO.size();
		
		Object pressO = jsonObject.get("press");
		JSONObject pressJO = (JSONObject) pressO;
		
		Object linkO = jsonObject.get("link");
		JSONObject linkJO = (JSONObject) linkO;
		
		Object dateO = jsonObject.get("date");
		JSONObject dateJO = (JSONObject) dateO;
		
		Object thumbnailO = jsonObject.get("thumbnail");
		JSONObject thumbnailJO = (JSONObject) thumbnailO;
		
		for (int i = 0; i < size; i++) {
			String title = (String) titleJO.get(i+"");
			String press = (String) pressJO.get(i+"");
			String link = (String) linkJO.get(i+"");
			String date = (String) dateJO.get(i+"");
			String thumbnail = (String) thumbnailJO.get(i+"");
			
			News temp = new News(title, press, link, date, thumbnail);
			crawlingMapper.insertNews(temp);
		}
		
		return 1;
	}
	
	@Override
	public int insertTwitts(Object obj) {
		JSONObject jsonObject = (JSONObject) obj;
		
		Object authorO = jsonObject.get("author");
		JSONObject authorJO = (JSONObject) authorO;
		// 가져온 트위터 개수
		int size = authorJO.size();
		
		Object contentO = jsonObject.get("contents");
		JSONObject contentJO = (JSONObject) contentO;
		
		Object dateO = jsonObject.get("date");
		JSONObject dateJO = (JSONObject) dateO;
		
		Pattern emoticons = Pattern.compile("[\\uD83C-\\uDBFF\\uDC00-\\uDFFF]+");
		
		for (int i = 0; i < size; i++) {
			String author = (String) authorJO.get(i+"");
			String content = (String) contentJO.get(i+"");
			String date = (String) dateJO.get(i+"");
			
			// 리트윗 제외
			if (content.subSequence(0, 2).equals("RT")) {
				continue;
			}

			Matcher emoticonsMatcher = emoticons.matcher(author);
			author = emoticonsMatcher.replaceAll("");
			// System.out.println(author + " " + content + " " + date);
			
			Twitter temp = new Twitter(author, content, date);
			crawlingMapper.insertTwitt(temp);
		}
		
		return 1;
	}
}
