package com.action.trends.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.action.trends.service.CrawlingService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class CrawlingController {

	@Autowired
	CrawlingService crawlingService;

	@ApiOperation(value = "뉴스 크롤링 파이썬 코드 실행")
	@PostMapping("/crawling/news")
	public void executeNewsCrawling() {
		String s = null;

		String keyword = "언컨택트";

		try {
			System.out.println("Executing python code");
			String pythonScriptPath = "/Users/donghwi/Desktop/Trend/python/news.py";
			String[] cmd = new String[3];
			cmd[0] = "python3";
			cmd[1] = pythonScriptPath;
			cmd[2] = keyword;

			Runtime rt = Runtime.getRuntime();
			Process process = rt.exec(cmd);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			// read the output from the command
			System.out.println("python should be run.");
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	@ApiOperation(value = "뉴스 데이터 불러와서 DB에 저장")
	@PostMapping("/load/news")
	public void loadNews() {
		String keyword = "언컨택트";
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader("/Users/donghwi/Desktop/Trend/python/" + keyword + "_news.json"));
			crawlingService.insertNews(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@ApiOperation(value = "제이슨 파일 불러와서 DB에 저장")
	@PostMapping("/python/json")
	public void loadJson() {
		String keyword = "멀티 페르소나";
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader("/Users/donghwi/Desktop/" + keyword + ".json"));
			crawlingService.insertTwitts(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
