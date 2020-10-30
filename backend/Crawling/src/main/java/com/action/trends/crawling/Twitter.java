package com.action.trends.crawling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Twitter {

	@GetMapping("/test")
	public void getTest() {
		String s;
		String keyword = "유재석";

		try {
			System.out.println("Executing python code");
			
			// 현재 절대 경로로 설정
			String pythonScriptPath = "C:/SSAFY/python_workspace/trendsaction/twitter.py";
			String[] cmd = new String[3];
			cmd[0] = "python";
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
}
