package com.action.trends.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
public class CrawlingController {
	
	@ApiOperation(value="파이썬 코드 실행", response = List.class)
	@PostMapping("/python")
	public void executePython() {
		String s = null;
		
		String keyword = "멀티 페르소나";
		
		try {
			System.out.println("Executing python code");
			// 경로는 서버에서 다시 잘~~ 설정해야함. 잊지 말길!
			String pythonScriptPath = "/Users/donghwi/Desktop/Trend/python/main.py";
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
			
			//System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
