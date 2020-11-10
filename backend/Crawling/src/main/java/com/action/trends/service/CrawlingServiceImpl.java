package com.action.trends.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.action.trends.dto.Twitter;
import com.action.trends.repository.CrawlingMapper;

@Service
public class CrawlingServiceImpl implements CrawlingService {
	
	@Autowired
	CrawlingMapper crawlingMapper;
	
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
